package capstone.recipable.domain.ingredient.controller.dto.response;

public record IngredientResponse(
        String ingredientCategory,
        String ingredientName
) {
    public static IngredientResponse of(String ingredientCategory, String ingredientName) {
        return new IngredientResponse(ingredientCategory, ingredientName);
    }
}
