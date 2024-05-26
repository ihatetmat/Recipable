package capstone.recipable.domain.ingredient.api.dto.response;

import java.util.List;

public record IngredientListResponse(
        List<String> ingredientResponseList
) {
    public static IngredientListResponse of(List<String> ingredients) {
        return new IngredientListResponse(ingredients);
    }
}
