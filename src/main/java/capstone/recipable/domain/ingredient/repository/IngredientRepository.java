package capstone.recipable.domain.ingredient.repository;

import capstone.recipable.domain.category.entity.Category;
import capstone.recipable.domain.ingredient.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByCategory(Category category);
}
