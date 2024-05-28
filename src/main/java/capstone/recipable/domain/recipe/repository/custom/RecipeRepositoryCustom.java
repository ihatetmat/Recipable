package capstone.recipable.domain.recipe.repository.custom;

import capstone.recipable.domain.recipe.entity.Recipe;
import capstone.recipable.domain.user.entity.User;

import java.util.List;

public interface RecipeRepositoryCustom {
    Recipe findRandomRecipe();

    List<Recipe> recentRecipes(User user);
}
