package capstone.recipable.domain.bookmark.repository;

import capstone.recipable.domain.bookmark.entity.Bookmark;
import capstone.recipable.domain.bookmark.repository.custom.BookmarkRepositoryCustom;
import capstone.recipable.domain.recipe.entity.Recipe;
import capstone.recipable.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long>, BookmarkRepositoryCustom {
    List<Bookmark> findAllByUser(User user);

    Bookmark findByUserAndRecipe(User user, Recipe recipe);
}
