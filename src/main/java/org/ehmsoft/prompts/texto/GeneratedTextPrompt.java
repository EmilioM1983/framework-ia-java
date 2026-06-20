package org.ehmsoft.prompts.texto;

public class GeneratedTextPrompt {
    public static String build(String text) {
        return """
                Redacta un artìculo de 100 palabras segun el tema especificado:

                %s
                """.formatted(text);
    }
}
