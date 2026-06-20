package org.ehmsoft.prompts.negocios;

public class FeedbackAnalyzerPrompt {
    public static String build(String feedback) {

        return """
                Analiza el siguiente feedback de cliente.

                Feedback:
                %s

                Devuelve:

                1. Sentimiento (Positivo, Neutral o Negativo)
                2. Problemas detectados
                3. Aspectos destacados
                4. Recomendaciones de mejora
                """
                .formatted(feedback);
    }
}
