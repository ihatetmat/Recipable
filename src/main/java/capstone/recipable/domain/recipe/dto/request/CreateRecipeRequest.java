package capstone.recipable.domain.recipe.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CreateRecipeRequest {

    private String recipeName;

    private String recipeImg;

    private String introduce;

    private String ingredients;

    private String recipeDetails;

    private String query;
}
