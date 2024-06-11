package capstone.recipable.domain.bookmark.repository.custom;

import capstone.recipable.domain.recipe.entity.Recipe;
import capstone.recipable.domain.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static capstone.recipable.domain.bookmark.entity.QBookmark.bookmark;

@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public boolean isBookmarked(User user, Recipe recipe) {
        return jpaQueryFactory
                .selectFrom(bookmark)
                .where(bookmark.user.eq(user).and(bookmark.recipe.eq(recipe)))
                .fetchOne() != null;
    }
}
