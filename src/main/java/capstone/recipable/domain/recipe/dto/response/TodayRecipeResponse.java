package capstone.recipable.domain.recipe.dto.response;

import capstone.recipable.domain.recipe.entity.Recipe;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class TodayRecipeResponse {

    private Long recipeId;

    private String recipeName;

    private String recipeImg;

    private String introduce;

    public static TodayRecipeResponse of(Recipe recipe) {
        return TodayRecipeResponse.builder()
                .recipeId(recipe.getId())
                .recipeName(recipe.getRecipeName())
                .recipeImg(recipe.getRecipeImg())
                .introduce(recipe.getIntroduce())
                .build();
    }
}
