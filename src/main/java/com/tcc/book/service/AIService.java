package com.tcc.book.service;

import com.tcc.book.utils.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class AIService {

    private final WebClient webClient;
    private final String apiKey;

    public AIService(WebClient.Builder webClientBuilder, @Value("${openai.api.key:}") String apiKey) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1").build();
        this.apiKey = apiKey;
    }

    public Response<String> generateBookInsight(String bookDescription) {
        Response<String> response = new Response<>();

        if (bookDescription == null || bookDescription.isBlank()) {
            response.setMessage("Book description is empty, cannot generate AI insights.");
            response.setErrorExist(true);
            return response;
        }

        String prompt = "Generate a short, engaging tagline for the following book description:\n\n" + bookDescription;

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(Map.of("role", "user", "content", prompt)),
                "max_tokens", 50
        );

        try {
            String aiGeneratedText = webClient.post()
                    .uri("/chat/completions")
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .map(responseMap -> {
                        List<Map<String, Object>> choices = (List<Map<String, Object>>) responseMap.get("choices");
                        if (choices != null && !choices.isEmpty()) {
                            return (String) ((Map<String, Object>) choices.get(0).get("message")).get("content");
                        }
                        return "AI response was empty.";
                    })
                    .block();

            response.setData(aiGeneratedText);
            response.setMessage("AI insights generated successfully.");
            response.setErrorExist(false);
        } catch (Exception e) {
            response.setMessage("Failed to generate AI insights: " + e.getMessage());
            response.setErrorExist(true);
        }

        return response;
    }
}
