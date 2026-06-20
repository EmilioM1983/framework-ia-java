package org.ehmsoft.prompts.agentes;

public class ProductRecommendationPrompt {

    public static String build(String query) {

        return """
                Analiza la consulta del usuario.

                Consulta:
                %s

                Categorías disponibles:
                - notebook
                - smartphone
                - auriculares

                Recomienda la mejor categoría para el usuario.
                Si la consulta no se relaciona con ninguna categoría, responde la siguiente frase : Lo siento no puedo ofrecerte el producto que busca en estos momentos.
                """.formatted(query);
    }
}
