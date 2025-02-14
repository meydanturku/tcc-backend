package com.tcc.book.service;

import com.tcc.book.enums.CommonValues;
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

    public AIService(WebClient.Builder webClientBuilder,
                     @Value("${openai.api.key}") String decryptedApiKey) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1").build();
        this.apiKey = decryptedApiKey;
    }

    public Response<String> generateBookInsight(String bookDescription) {

        Response<String> resp = new Response<>();

        String prompt = "Generate a short, engaging tagline for the following book description:\n\n" + bookDescription;

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(Map.of("role", "user", "content", prompt)),
                "max_tokens", 50
        );

        String block = webClient.post()
                .uri("/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> (String) ((Map<String, Object>) ((List<Object>) response.get("choices")).get(0)).get("message"))
                .block();

        resp.setData(block);
        resp.setMessage(CommonValues.BOOKS_AI_DESCRIPTION_SUCCESSFULLY.getMessage());
        resp.setErrorExist(false);
        return resp;
    }
}
