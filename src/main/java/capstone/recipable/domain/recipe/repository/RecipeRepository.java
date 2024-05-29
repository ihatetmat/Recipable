package capstone.recipable.domain.recipe.repository;

import capstone.recipable.domain.recipe.entity.Recipe;
import capstone.recipable.domain.recipe.repository.custom.RecipeRepositoryCustom;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long>, RecipeRepositoryCustom {

}
