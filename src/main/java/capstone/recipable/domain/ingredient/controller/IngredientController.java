package capstone.recipable.domain.ingredient.controller;

import capstone.recipable.domain.ingredient.controller.dto.response.IngredientListResponse;
import capstone.recipable.domain.ingredient.service.NaverOcrService;
import capstone.recipable.domain.ingredient.service.NaverSearchImageService;
import capstone.recipable.domain.ingredient.service.PredictService;
import capstone.recipable.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "ingredient", description = "영수증 분석 및 재료 관련 api")
public class IngredientController {
    private final NaverOcrService naverOcrService;
    private final NaverSearchImageService naverSearchImageService;
    private final PredictService predictService;

    @Operation(summary = "영수증 업로드 후 재료 카테고리 및 재료 이름 반환 api", description = """
            form/data 형식으로 영수증 이미지를 업로드 하면
            
            상품명 텍스트 추출 후 재료 카테고리, 재료 이름을 반환해줍니다.
            
            """)
    @PostMapping(value = "/receipt", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<IngredientListResponse>> getIngredientsByOcr(@RequestPart(value = "multipartFile")
                                                                                       MultipartFile multipartFile) {

        List<String> ingredientList = naverOcrService.callApi(multipartFile);
        IngredientListResponse ingredientListResponse = predictService.returnPrediction(ingredientList);
        return SuccessResponse.of(ingredientListResponse);
    }

    @GetMapping("/test")
    public ResponseEntity<SuccessResponse<String>> getImageByNaverSearch(@RequestParam String query) {
        String imageFromNaverSearchApi = naverSearchImageService.getImageFromNaverSearchApi(query);
        return SuccessResponse.of(imageFromNaverSearchApi);
    }

}
