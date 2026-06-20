package org.ehmsoft.factory;

import org.ehmsoft.agents.IAgent;
import org.ehmsoft.agents.agentes.ProductRecommendationAgent;
import org.ehmsoft.agents.negocios.FeedbackAnalyzerAgent;
import org.ehmsoft.agents.texto.GeneratedTextAgent;
import org.ehmsoft.agents.texto.SummarizerAgent;
import org.ehmsoft.agents.texto.TranslatorTextAgent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgentFactoryTest {

    @Test
    void create_withOption1_shouldReturnSummarizerAgent() {
        IAgent agent = AgentFactory.create(1);
        assertInstanceOf(SummarizerAgent.class, agent);
    }

    @Test
    void create_withOption2_shouldReturnGeneratedTextAgent() {
        IAgent agent = AgentFactory.create(2);
        assertInstanceOf(GeneratedTextAgent.class, agent);
    }

    @Test
    void create_withOption3_shouldReturnTranslatorTextAgent() {
        IAgent agent = AgentFactory.create(3);
        assertInstanceOf(TranslatorTextAgent.class, agent);
    }

    @Test
    void create_withOption4_shouldReturnProductRecommendationAgent() {
        IAgent agent = AgentFactory.create(4);
        assertInstanceOf(ProductRecommendationAgent.class, agent);
    }

    @Test
    void create_withOption5_shouldReturnFeedbackAnalyzerAgent() {
        IAgent agent = AgentFactory.create(5);
        assertInstanceOf(FeedbackAnalyzerAgent.class, agent);
    }

    @Test
    void create_withInvalidOption_shouldThrowException() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> AgentFactory.create(99));
        assertEquals("Agente no válido", ex.getMessage());
    }

    @Test
    void create_withNegativeOption_shouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> AgentFactory.create(-1));
    }

    @Test
    void create_shouldReturnNonNullAgentForAllValidOptions() {
        for (int i = 1; i <= 5; i++) {
            assertNotNull(AgentFactory.create(i),
                    "Option " + i + " should return a non-null agent");
        }
    }
}