package capstone.recipable.domain.ingredient.service;

import capstone.recipable.domain.ingredient.controller.dto.response.IngredientListResponse;
import capstone.recipable.domain.ingredient.controller.dto.response.IngredientResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PredictService {
    @Value("${model.url}")
    private String url;

    public IngredientListResponse returnPrediction(List<String> products) {
        String formattedList = products.stream()
                .map(item -> "\"" + item + "\"")
                .collect(Collectors.joining(", "));

        String body = "[" + formattedList + "]";

        String requestBody = "{\"sentences\": " + body + "}";
        
        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // HTTP 요청 생성
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        // RestTemplate 생성
        RestTemplate restTemplate = new RestTemplate();

        // POST 요청 전송
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, List<String>> responseMap = objectMapper.readValue(responseBody, new TypeReference<Map<String, List<String>>>(){});
            System.out.println("responseMap = " + responseMap);
            List<String> result = responseMap.get("predictions");

            List<IngredientResponse> list = result.stream()
                    .map(s -> {
                        String[] splitString = s.split("/", 2);
                        return IngredientResponse.of(splitString[0].trim(), splitString[1].trim());
                    })
                    .toList();

            return IngredientListResponse.of(list);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
