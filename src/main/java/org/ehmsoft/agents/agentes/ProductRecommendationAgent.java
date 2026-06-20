package org.ehmsoft.agents.agentes;

import org.ehmsoft.agents.IAgent;
import org.ehmsoft.client.OllamaClient;
import org.ehmsoft.config.ProductCatalog;
import org.ehmsoft.prompts.agentes.ProductRecommendationPrompt;

public class ProductRecommendationAgent implements IAgent {
    private final OllamaClient client;

    public ProductRecommendationAgent(OllamaClient client) {
        this.client = client;
    }

    @Override
    public String execute(String query) throws Exception {

        String aiResponse = client.generate(ProductRecommendationPrompt.build(query));

        for (String category : ProductCatalog.PRODUCTS.keySet()) {

            if (query.toLowerCase().contains(category)) {

                return String.join(
                        "\n",
                        ProductCatalog.PRODUCTS.get(category)
                );
            }
        }

        return aiResponse;
    }
}
