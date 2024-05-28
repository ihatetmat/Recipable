package capstone.recipable.domain.ingredient.controller.dto.response;

import java.time.LocalDate;

public record IngredientDetailResponse(
        String ingredientName,
        String ingredientImage,
        String categoryName,
        LocalDate expirationDay,
        String memo
) {
    public static IngredientDetailResponse of(String ingredientName,String ingredientImage, String categoryName, LocalDate expirationDay, String memo) {
        return new IngredientDetailResponse(ingredientName, ingredientImage, categoryName, expirationDay, memo);
    }

}
