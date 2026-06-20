package org.ehmsoft.agents.agentes;

import org.ehmsoft.client.OllamaClient;
import org.ehmsoft.config.ProductCatalog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductRecommendationAgentTest {

    @Mock
    private OllamaClient ollamaClient;

    private ProductRecommendationAgent agent;

    @BeforeEach
    void setUp() {
        agent = new ProductRecommendationAgent(ollamaClient);
    }

    @Test
    void execute_withNotebookQuery_shouldReturnCatalogProducts() throws Exception {
        String result = agent.execute("Quiero una notebook para programar");

        assertTrue(result.contains("Apple MacBook Air M2"));
        assertTrue(result.contains("Dell XPS 13"));
        assertTrue(result.contains("Lenovo ThinkPad X1 Carbon"));
        verify(ollamaClient, times(1)).generate(anyString());
    }

    @Test
    void execute_withSmartphoneQuery_shouldReturnCatalogProducts() throws Exception {
        String result = agent.execute("smartphone gama alta");

        assertTrue(result.contains("iPhone 14"));
        assertTrue(result.contains("Samsung Galaxy S23"));
        assertTrue(result.contains("Google Pixel 7"));
        verify(ollamaClient, times(1)).generate(anyString());
    }

    @Test
    void execute_withAuricularesQuery_shouldReturnCatalogProducts() throws Exception {
        String result = agent.execute("auriculares inalambricos");

        assertTrue(result.contains("Sony WH-1000XM4"));
        assertTrue(result.contains("Bose QuietComfort 45"));
        assertTrue(result.contains("Anker Soundcore Life Q30"));
        verify(ollamaClient, times(1)).generate(anyString());
    }

    @Test
    void execute_withUnknownQuery_shouldFallbackToAIResponse() throws Exception {
        String aiResponse = "Lo siento no puedo ofrecerte el producto que busca en estos momentos.";
        when(ollamaClient.generate(anyString())).thenReturn(aiResponse);

        String result = agent.execute("mesa de escritorio");

        assertEquals(aiResponse, result);
        verify(ollamaClient, times(1)).generate(anyString());
    }

    @Test
    void execute_withMixedCaseQuery_shouldMatchCategory() throws Exception {
        String result = agent.execute("NOTEBOOK gaming");

        assertTrue(result.contains("Apple MacBook Air M2"));
        verify(ollamaClient, times(1)).generate(anyString());
    }

    @Test
    void productCatalog_shouldContainThreeCategories() {
        assertEquals(3, ProductCatalog.PRODUCTS.size());
        assertTrue(ProductCatalog.PRODUCTS.containsKey("notebook"));
        assertTrue(ProductCatalog.PRODUCTS.containsKey("smartphone"));
        assertTrue(ProductCatalog.PRODUCTS.containsKey("auriculares"));
    }

    @Test
    void productCatalog_shouldHaveThreeProductsPerCategory() {
        assertEquals(3, ProductCatalog.PRODUCTS.get("notebook").size());
        assertEquals(3, ProductCatalog.PRODUCTS.get("smartphone").size());
        assertEquals(3, ProductCatalog.PRODUCTS.get("auriculares").size());
    }
}