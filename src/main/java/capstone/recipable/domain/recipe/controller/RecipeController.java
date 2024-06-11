package capstone.recipable.domain.recipe.controller;

import capstone.recipable.domain.ingredient.service.NaverSearchImageService;
import capstone.recipable.domain.recipe.dto.request.CreateRecipeRequest;
import capstone.recipable.domain.recipe.dto.response.CreateRecipeResponse;
import capstone.recipable.domain.recipe.dto.response.ImageResponse;
import capstone.recipable.domain.recipe.dto.response.RecipeDetailsResponse;
import capstone.recipable.domain.recipe.dto.response.RecipeVideoResponse;
import capstone.recipable.domain.recipe.service.RecipeService;
import capstone.recipable.domain.recipe.service.YoutubeService;
import capstone.recipable.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recipe")
@Tag(name = "recipe", description = "레시피 관련 api")
public class RecipeController {

    private final RecipeService recipeService;
    private final NaverSearchImageService naverSearchImageService;

    @Operation(summary = "이미지 가져오기 api", description = """
            이미지 가져오기 api입니다.
            
            RequestParam으로 imageName을 받습니다.
            
            불러오고 싶은 이미지를 입력해주세요. ex)사과
            """)
    @GetMapping("/image")
    public ResponseEntity<SuccessResponse<ImageResponse>> getImage(@RequestParam String imageName) {

        String image = naverSearchImageService.getImageFromNaverSearchApi(imageName);
        ImageResponse response = ImageResponse.of(image);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "레시피 생성 api", description = """
            레시피 상세 조회 api입니다.
            
            레시피 이미지와 gpt 결과를 보내주시면 레시피를 생성하고 저장합니다.
            
            저장한 레시피와 레시피 관련 영상을 반환해줍니다.
            """)
    @PostMapping
    public ResponseEntity<SuccessResponse<CreateRecipeResponse>> createRecipe(@RequestBody CreateRecipeRequest request) throws IOException {
        CreateRecipeResponse response = recipeService.createRecipe(request);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "레시피 상세 조회 api", description = """
            레시피 상세 조회 api입니다.
            
            """)
    @GetMapping("/{recipeId}")
    public ResponseEntity<SuccessResponse<RecipeDetailsResponse>> getRecipeDetails(@PathVariable long recipeId) {
        RecipeDetailsResponse response = recipeService.getRecipeDetails(recipeId);
        return SuccessResponse.of(response);
    }
}
