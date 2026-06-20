package org.ehmsoft.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ehmsoft.config.OllamaConfig;
import org.ehmsoft.dtos.OllamaRequestDto;
import org.ehmsoft.dtos.OllamaResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OllamaClient {
    private final OllamaConfig config = new OllamaConfig();
    private static final Logger LOGGER = LoggerFactory.getLogger(OllamaClient.class);
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public String generate(String prompt) throws Exception {
        try {
            LOGGER.info("Enviando prompt a Ollama: {}", config.getModel());

            OllamaRequestDto requestBody =
                    new OllamaRequestDto(
                            config.getModel(),
                            prompt,
                            false
                    );

            String json = mapper.writeValueAsString(requestBody);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(config.getUrl()))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            LOGGER.info(
                    "Respuesta recibida. Status: {}",
                    response.statusCode()
            );

            OllamaResponseDto result =
                    mapper.readValue(response.body(), OllamaResponseDto.class);

            if (result == null
                    || result.response() == null
                    || result.response().isBlank()) {

                LOGGER.error("Ollama devolvió una respuesta vacía");

                throw new RuntimeException(
                        "Respuesta vacía recibida desde Ollama"
                );
            }

            return result.response();


        } catch (Exception e) {
            LOGGER.error("Error calling Ollama API", e);
            throw e;
        }
    }
}
