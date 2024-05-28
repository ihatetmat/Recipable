package capstone.recipable.domain.user.dto.response;

import capstone.recipable.domain.recipe.dto.response.RecentRecipeResponse;
import capstone.recipable.domain.recipe.dto.response.TodayRecipeResponse;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class MainPageResponse {

    private TodayRecipeResponse todayRecipe;

    private List<RecentRecipeResponse> recentRecipes;

    public static MainPageResponse of(TodayRecipeResponse todayRecipe, List<RecentRecipeResponse> recentRecipes) {
        return MainPageResponse.builder()
                .todayRecipe(todayRecipe)
                .recentRecipes(recentRecipes)
                .build();
    }
}
