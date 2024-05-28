package capstone.recipable.domain.recipe.controller;

import capstone.recipable.domain.recipe.dto.request.CreateRecipeRequest;
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
    private final YoutubeService youtubeService;

    /*@Operation(summary = "유튜브 api", description = """
            유튜브 영상 첨부 api입니다.
            
            param으로 keyword를 보내주시면 유튜브에서 검색하여 영상 3개를 반환해줍니다.
            
            """)
    @GetMapping("/videos")
    public ResponseEntity<List<RecipeVideoResponse>> searchVideo(@RequestParam String keyword) throws IOException {
        List<RecipeVideoResponse> results = youtubeService.searchVideo(keyword);
        return ResponseEntity.ok(results);
    }*/

    @Operation(summary = "레시피 생성 api", description = """
            레시피 상세 조회 api입니다.
            
            gpt 결과를 보내주시면 레시피를 생성하고 저장합니다.
            
            저장한 레시피와 레시피 관련 영상을 반환해줍니다.
            """)
    @PostMapping
    public ResponseEntity<SuccessResponse<RecipeDetailsResponse>> createRecipe(@RequestBody CreateRecipeRequest request) throws IOException {
        RecipeDetailsResponse response = recipeService.createRecipe(request);
        return SuccessResponse.of(response);
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<SuccessResponse<RecipeDetailsResponse>> getRecipeDetails(@PathVariable long recipeId) {
        RecipeDetailsResponse response = recipeService.getRecipeDetails(recipeId);
        return SuccessResponse.of(response);
    }
}
