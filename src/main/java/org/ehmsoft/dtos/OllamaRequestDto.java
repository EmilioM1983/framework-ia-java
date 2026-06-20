package org.ehmsoft.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OllamaRequestDto(
        String model,
        String prompt,
        boolean stream
) {}
