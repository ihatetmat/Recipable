package capstone.recipable.domain.ingredient.controller.dto.response;

import java.util.List;

public record IngredientListResponse(
        List<IngredientResponse> ingredientResponseList
) {
    public static IngredientListResponse of(List<IngredientResponse> ingredients) {
        return new IngredientListResponse(ingredients);
    }
}
