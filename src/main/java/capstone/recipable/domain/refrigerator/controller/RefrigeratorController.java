package capstone.recipable.domain.refrigerator.controller;

import capstone.recipable.domain.ingredient.controller.dto.request.UpdateIngredientRequest;
import capstone.recipable.domain.ingredient.controller.dto.response.IngredientDetailResponse;
import capstone.recipable.domain.refrigerator.dto.CreateIngredientListRequest;
import capstone.recipable.domain.refrigerator.dto.response.RefrigeratorListResponse;
import capstone.recipable.domain.refrigerator.service.CreateIngredientService;
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
    private final CreateIngredientService createIngredientService;

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

    @Operation(summary = "냉장고 안 식재료 수정 api", description = """
                        
            사용자 냉장고 안에 있는 식재료를 수정 합니다.
                        
            """)
    @PatchMapping("/{ingredientId}")
    public ResponseEntity<SuccessResponse<IngredientDetailResponse>> updateIngredient(@PathVariable Long ingredientId,
                                                                                      @RequestBody UpdateIngredientRequest updateIngredientRequest) {

        IngredientDetailResponse ingredientDetail = refrigeratorService.updateIngredient(ingredientId, updateIngredientRequest);
        return SuccessResponse.of(ingredientDetail);
    }

    @Operation(summary = "냉장고 안 식재료 삭제 api", description = """
                        
            사용자 냉장고 안에 있는 식재료를 삭제 합니다.
                        
            """)
    @DeleteMapping("/{ingredientId}")
    public ResponseEntity<SuccessResponse<String>> updateIngredient(@PathVariable Long ingredientId) {

        refrigeratorService.deleteIngredient(ingredientId);
        return SuccessResponse.of("식재료가 성공적으로 삭제 되었습니다.");
    }

    @Operation(summary = "냉장고에 재료 추가 api", description = """
                        
            영수증분석을 통해 나온 재료들을 통해서 냉장고에 재료를 추가합니다.
                        
            """)
    @PostMapping("/receipt")
    public ResponseEntity<SuccessResponse<String>> updateIngredient(@RequestBody CreateIngredientListRequest createIngredientListRequest) {
        createIngredientService.createIngredient(createIngredientListRequest);
        return SuccessResponse.of("식재료가 성공적으로 냉장고에 추가되었습니다.");
    }





}
