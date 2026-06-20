package org.ehmsoft.agents.texto;

import org.ehmsoft.agents.IAgent;
import org.ehmsoft.client.OllamaClient;
import org.ehmsoft.prompts.texto.SummarizePrompt;

public class SummarizerAgent implements IAgent {

    private final OllamaClient client;

    public SummarizerAgent(OllamaClient client) {
        this.client = client;
    }

    @Override
    public String execute(String text) throws Exception {
        return client.generate(
                SummarizePrompt.build(text)
        );
    }
}