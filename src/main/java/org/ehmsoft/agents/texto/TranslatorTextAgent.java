package org.ehmsoft.agents.texto;

import org.ehmsoft.agents.IAgent;
import org.ehmsoft.client.OllamaClient;
import org.ehmsoft.prompts.texto.TranslatorTextPrompt;

public class TranslatorTextAgent implements IAgent {
    private final OllamaClient client;

    public TranslatorTextAgent(OllamaClient client) {
        this.client = client;
    }
    @Override
    public String execute(String prompt) throws Exception {
        String finalPrompt = TranslatorTextPrompt.build(prompt);
        return client.generate(finalPrompt);
    }
}
