package capstone.recipable.domain.refrigerator.controller;

import capstone.recipable.domain.ingredient.controller.dto.response.IngredientDetailResponse;
import capstone.recipable.domain.refrigerator.dto.response.RefrigeratorListResponse;
import capstone.recipable.domain.refrigerator.service.RefrigeratorService;
import capstone.recipable.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/refrigerators")
@Tag(name = "refrigerator", description = "냉장고 관련 api")
public class RefrigeratorController {
    private final RefrigeratorService refrigeratorService;

    @Operation(summary = "냉장고 안 식재료 전체 조회 api", description = """
            
            사용자 냉장고 안에 있는 식재료를 전체 조회합니다.
            
            """)
    @GetMapping
    public ResponseEntity<SuccessResponse<RefrigeratorListResponse>> getAllIngredientsByRefrigerator() {
        RefrigeratorListResponse allIngredientsByRefrigerator = refrigeratorService.getAllIngredientsByRefrigerator();
        return SuccessResponse.of(allIngredientsByRefrigerator);
    }

    @Operation(summary = "냉장고 안 식재료 상세 조회 api", description = """
                        
            사용자 냉장고 안에 있는 식재료를 상세 조회합니다.
                        
            """)
    @GetMapping("/{ingredientId}")
    public ResponseEntity<SuccessResponse<IngredientDetailResponse>> getIngredients(@PathVariable Long ingredientId) {
        IngredientDetailResponse ingredientDetail = refrigeratorService.getIngredient(ingredientId);
        return SuccessResponse.of(ingredientDetail);
    }

}
