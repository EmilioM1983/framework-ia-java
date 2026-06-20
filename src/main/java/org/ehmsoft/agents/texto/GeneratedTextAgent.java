package org.ehmsoft.agents.texto;

import org.ehmsoft.agents.IAgent;
import org.ehmsoft.client.OllamaClient;
import org.ehmsoft.prompts.texto.GeneratedTextPrompt;

public class GeneratedTextAgent implements IAgent {
    private final OllamaClient client;

    public GeneratedTextAgent(OllamaClient client) {
        this.client = client;
    }

    @Override
    public String execute(String text) throws Exception {
        return client.generate(GeneratedTextPrompt.build(text));
    }
}