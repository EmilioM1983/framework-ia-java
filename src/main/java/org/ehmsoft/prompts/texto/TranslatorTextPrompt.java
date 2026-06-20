package org.ehmsoft.prompts.texto;

public class TranslatorTextPrompt {
    public static String build(String text) {
        return """
                Traduce el siguiente texto al español:
                
                %s
                """.formatted(text);
    }
}
