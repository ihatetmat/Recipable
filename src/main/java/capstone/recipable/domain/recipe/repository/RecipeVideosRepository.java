package capstone.recipable.domain.recipe.repository;

import capstone.recipable.domain.recipe.entity.RecipeVideos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeVideosRepository extends JpaRepository<RecipeVideos, Long> {
}
