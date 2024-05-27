package capstone.recipable.domain.ingredient.controller.dto.request;

import capstone.recipable.domain.category.entity.Category;
import capstone.recipable.domain.ingredient.entity.Ingredient;

import java.time.LocalDate;

public record UpdateIngredientRequest(
        String ingredientImage,
        String ingredientName,
        String categoryName,
        LocalDate expirationDay,
        String memo
) {

}
