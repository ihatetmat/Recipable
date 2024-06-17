package capstone.recipable.domain.recipe.dto.response;

import capstone.recipable.domain.bookmark.repository.BookmarkRepository;
import capstone.recipable.domain.recipe.entity.Recipe;
import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class CreateRecipeResponse {
    private Long recipeId;

    private String recipeName;

    private String introduce;

    private String recipeImg;

    private String ingredients;

    @Column(length = 3000)
    private String recipeDetails;

    private List<RecipeVideoResponse> recipeVideoResponses;

    private boolean bookmark = false;

    public static CreateRecipeResponse of(Recipe recipe) {
        List<RecipeVideoResponse> responses = recipe.getRecipeVideos().stream()
                .map(recipeVideos -> RecipeVideoResponse.of(recipeVideos.getVideoUrl(),
                        recipeVideos.getTitle(),
                        recipeVideos.getThumbnail()))
                .toList();

        return CreateRecipeResponse.builder()
                .recipeId(recipe.getId())
                .recipeName(recipe.getRecipeName())
                .introduce(recipe.getIntroduce())
                .recipeImg(recipe.getRecipeImg())
                .ingredients(recipe.getIngredients())
                .recipeDetails(recipe.getRecipeDetails())
                .recipeVideoResponses(responses)
                .build();
    }
}
