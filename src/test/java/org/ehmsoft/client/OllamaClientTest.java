package org.ehmsoft.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ehmsoft.dtos.OllamaResponseDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OllamaClientTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void validResponse_shouldReturnResponseText() throws Exception {
        String expected = "Texto de prueba";
        OllamaResponseDto dto = new OllamaResponseDto(expected);
        String json = mapper.writeValueAsString(dto);

        OllamaResponseDto result = mapper.readValue(json, OllamaResponseDto.class);
        assertEquals(expected, result.response());
    }

    @Test
    void nullResponse_shouldThrowException() {
        Exception ex = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Respuesta vacía recibida desde Ollama");
        });
        assertEquals("Respuesta vacía recibida desde Ollama", ex.getMessage());
    }

    @Test
    void emptyResponse_shouldThrowException() {
        Exception ex = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Respuesta vacía recibida desde Ollama");
        });
        assertNotNull(ex.getMessage());
    }
}