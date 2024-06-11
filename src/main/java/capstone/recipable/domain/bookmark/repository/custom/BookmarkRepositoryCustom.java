package capstone.recipable.domain.bookmark.repository.custom;

import capstone.recipable.domain.recipe.entity.Recipe;
import capstone.recipable.domain.user.entity.User;

public interface BookmarkRepositoryCustom {
    boolean isBookmarked(User user, Recipe recipe);
}
