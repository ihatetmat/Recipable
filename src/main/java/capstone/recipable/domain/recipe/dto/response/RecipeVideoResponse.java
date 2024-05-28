package capstone.recipable.domain.recipe.dto.response;

import capstone.recipable.domain.recipe.entity.RecipeVideos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class RecipeVideoResponse {

    private String videoUrl;

    private String title;

    private String thumbnail;

    public static RecipeVideoResponse of(String videoUrl, String title, String thumbnail) {
        return RecipeVideoResponse.builder()
                .videoUrl(videoUrl)
                .title(title)
                .thumbnail(thumbnail)
                .build();
    }
}
