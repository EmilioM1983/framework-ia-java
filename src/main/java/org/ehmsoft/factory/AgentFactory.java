package org.ehmsoft.factory;


import org.ehmsoft.agents.IAgent;
import org.ehmsoft.agents.agentes.ProductRecommendationAgent;
import org.ehmsoft.agents.negocios.FeedbackAnalyzerAgent;
import org.ehmsoft.agents.texto.GeneratedTextAgent;
import org.ehmsoft.agents.texto.SummarizerAgent;
import org.ehmsoft.agents.texto.TranslatorTextAgent;
import org.ehmsoft.client.OllamaClient;


public class AgentFactory {

    private static final OllamaClient CLIENT =
            new OllamaClient();

        public static IAgent create(int option) {

            return switch (option) {

                case 1 -> new SummarizerAgent(CLIENT);

                case 2 -> new GeneratedTextAgent(CLIENT);

                case 3 -> new TranslatorTextAgent(CLIENT);

                case 4 -> new ProductRecommendationAgent(CLIENT);

                case 5 -> new FeedbackAnalyzerAgent(CLIENT);

                default ->
                        throw new IllegalArgumentException("Agente no válido");
        };
    }
}