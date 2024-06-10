package capstone.recipable.domain.recipe.service;

import capstone.recipable.domain.auth.jwt.SecurityContextProvider;
import capstone.recipable.domain.recipe.dto.response.RecipeVideoResponse;
import capstone.recipable.domain.recipe.entity.RecipeVideos;
import capstone.recipable.domain.recipe.repository.RecipeVideosRepository;
import capstone.recipable.global.error.ApplicationException;
import capstone.recipable.global.error.ErrorCode;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class YoutubeService {

    @Value("${youtube.api.key}")
    private String apiKey;

    private final RecipeVideosRepository recipeVideosRepository;

    public List<RecipeVideoResponse> searchVideo(String query) throws IOException {
        // JSON 데이터를 처리하기 위한 JsonFactory 객체 생성
        JsonFactory jsonFactory = new JacksonFactory();

        // YouTube 객체를 빌드하여 API에 접근할 수 있는 YouTube 클라이언트 생성
        YouTube youtube = new YouTube.Builder(
                new com.google.api.client.http.javanet.NetHttpTransport(),
                jsonFactory,
                request -> {})
                .build();

        // YouTube Search API를 사용하여 동영상 검색을 위한 요청 객체 생성
        YouTube.Search.List search = youtube.search().list(Collections.singletonList("id,snippet"));

        search.setKey(apiKey);
        search.setQ(query);
        SearchListResponse searchResponse = search.execute();
        List<SearchResult> searchResultList = searchResponse.getItems();

        if (searchResultList != null && !searchResultList.isEmpty()) {
            List<RecipeVideoResponse> responses = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                SearchResult searchResult = searchResultList.get(i);

                String videoId = searchResult.getId().getVideoId();
                String videoTitle = searchResult.getSnippet().getTitle();
                String thumbnail = searchResult.getSnippet().getThumbnails().getDefault().getUrl();

                RecipeVideoResponse response = RecipeVideoResponse.of(videoId, videoTitle, thumbnail);
                responses.add(response);
            }
            return responses;
        }
        else{
            throw new ApplicationException(ErrorCode.INVALID_BODY);
        }
    }
}
