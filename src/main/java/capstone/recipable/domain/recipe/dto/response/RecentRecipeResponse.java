package capstone.recipable.domain.recipe.dto.response;

import capstone.recipable.domain.recipe.entity.Recipe;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class RecentRecipeResponse {

    private Long recipeId;

    private String recipeImg;

    private String introduce;

    public static RecentRecipeResponse of(Recipe recipe) {
        return RecentRecipeResponse.builder()
                .recipeId(recipe.getId())
                .recipeImg(recipe.getRecipeImg())
                .introduce(recipe.getIntroduce())
                .build();
    }
}
