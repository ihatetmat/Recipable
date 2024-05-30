package capstone.recipable.domain.recipe.service;

import capstone.recipable.domain.ingredient.service.NaverSearchImageService;
import capstone.recipable.domain.recipe.dto.request.CreateRecipeRequest;
import capstone.recipable.domain.recipe.dto.response.RecipeDetailsResponse;
import capstone.recipable.domain.recipe.entity.Recipe;
import capstone.recipable.domain.recipe.entity.RecipeVideos;
import capstone.recipable.domain.recipe.repository.RecipeRepository;
import capstone.recipable.global.error.ApplicationException;
import capstone.recipable.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final YoutubeService youtubeService;
    private final NaverSearchImageService naverSearchImageService;

    //레시피 상세 조회
    public RecipeDetailsResponse getRecipeDetails(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.RECIPE_NOT_FOUND));

        return RecipeDetailsResponse.of(recipe);
    }

    //레시피 생성
    @Transactional
    public RecipeDetailsResponse createRecipe(CreateRecipeRequest request) throws IOException {
        List<RecipeVideos>  recipeVideos = youtubeService.searchVideo(request.getQuery());
        Recipe recipe = Recipe.of(request.getRecipeImg(), request.getRecipeName(), request.getIntroduce(),
                request.getIngredients(), request.getRecipeDetails(), recipeVideos);

        recipeRepository.save(recipe);

        return RecipeDetailsResponse.of(recipe);
    }
}
