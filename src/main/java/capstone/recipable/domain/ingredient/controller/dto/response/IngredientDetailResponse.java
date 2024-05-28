package capstone.recipable.domain.ingredient.controller.dto.response;

import java.time.LocalDate;

public record IngredientDetailResponse(
        String ingredientName,
        String categoryName,
        LocalDate expirationDay,
        String memo
) {
    public static IngredientDetailResponse of(String ingredientName, String categoryName, LocalDate expirationDay, String memo) {
        return new IngredientDetailResponse(ingredientName, categoryName, expirationDay, memo);
    }
}
