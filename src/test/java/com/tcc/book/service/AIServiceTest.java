package com.tcc.book.service;

import com.tcc.book.utils.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;

public class AIServiceTest {

    private AIService aiService;

    private static final String MOCK_API_KEY = "sk-test-api-key";

    @BeforeEach
    void setup() {
        aiService = new AIService(WebClient.builder(), MOCK_API_KEY);
    }

    @Test
    public void shouldReturnErrorForEmptyDescription() {
        Response<String> response = aiService.generateBookInsight("");

        assertTrue(response.getErrorExist());
        assertEquals("Book description is empty, cannot generate AI insights.", response.getMessage());
    }

    @Test
    public void shouldReturnErrorForNullDescription() {
        Response<String> response = aiService.generateBookInsight(null);

        assertTrue(response.getErrorExist());
        assertEquals("Book description is empty, cannot generate AI insights.", response.getMessage());
    }
}
