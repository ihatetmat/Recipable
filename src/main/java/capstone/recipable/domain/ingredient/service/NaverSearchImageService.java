package capstone.recipable.domain.ingredient.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.Charset;

@Component
public class NaverSearchImageService {
    @Value("${naver.service.client-id}")
    private String clientId;

    @Value("${naver.service.client-secret}")
    private String clientSecret;

    public String getImageFromNaverSearchApi(String imageName) {

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/image")
                .queryParam("query", imageName)
                .queryParam("display", 1)
                .queryParam("start", 1)
                .queryParam("sort", "sim")
                .queryParam("filter","medium")
                .encode(Charset.forName("UTF-8"))
                .build()
                .toUri();

        RequestEntity<Void> requestEntity = RequestEntity.get(uri)
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .build();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);

        String responseBody = response.getBody();
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray items = jsonObject.getJSONArray("items");

        if (!items.isEmpty()) {
            return items.getJSONObject(0).getString("link");
        }
        else {
            return null;
        }
    }

}
