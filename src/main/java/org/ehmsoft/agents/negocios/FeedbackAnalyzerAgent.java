package org.ehmsoft.agents.negocios;

import org.ehmsoft.agents.IAgent;
import org.ehmsoft.client.OllamaClient;
import org.ehmsoft.prompts.negocios.FeedbackAnalyzerPrompt;

public class FeedbackAnalyzerAgent implements IAgent {
    private final OllamaClient client;

    public FeedbackAnalyzerAgent(OllamaClient client) {
        this.client = client;
    }

    @Override
    public String execute(String text) throws Exception {

        return client.generate(
                FeedbackAnalyzerPrompt.build(text)
        );
    }
}
