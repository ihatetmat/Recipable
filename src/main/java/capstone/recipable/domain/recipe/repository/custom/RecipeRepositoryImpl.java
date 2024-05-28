package capstone.recipable.domain.recipe.repository.custom;

import capstone.recipable.domain.recipe.entity.Recipe;
import capstone.recipable.domain.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static capstone.recipable.domain.recipe.entity.QRecipe.recipe;

@RequiredArgsConstructor
public class RecipeRepositoryImpl implements RecipeRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Recipe findRandomRecipe() {

        return jpaQueryFactory
                .select(recipe)
                .from(recipe)
                .orderBy(com.querydsl.core.types.dsl.Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .limit(1)
                .fetchOne();
    }

    @Override
    public List<Recipe> recentRecipes(User user){
        return jpaQueryFactory
                .selectFrom(recipe)
                .where(recipe.user.eq(user))
                .orderBy(recipe.id.desc())
                .limit(3)
                .fetch();
    }
}
