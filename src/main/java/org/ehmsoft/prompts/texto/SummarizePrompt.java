package org.ehmsoft.prompts.texto;

public class SummarizePrompt {
    public static String build(String text) {
        return """
                Resume el siguiente texto en 25 palabras:

                %s
                """.formatted(text);
    }
}
