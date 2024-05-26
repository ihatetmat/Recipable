package capstone.recipable.domain.ingredient.controller;

import capstone.recipable.domain.ingredient.controller.dto.response.IngredientListResponse;
import capstone.recipable.domain.ingredient.service.NaverOcrService;
import capstone.recipable.domain.ingredient.service.PredictService;
import capstone.recipable.global.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IngredientController {
    private final NaverOcrService naverOcrService;
    private final PredictService predictService;

    @PostMapping("/receipt")
    public ResponseEntity<SuccessResponse<IngredientListResponse>> getIngredientsByOcr(@RequestPart(value = "multipartFile")
                                                                   MultipartFile multipartFile) {

        List<String> ingredientList = naverOcrService.callApi(multipartFile);
        IngredientListResponse ingredientListResponse = predictService.returnPrediction(ingredientList);
        return SuccessResponse.of(ingredientListResponse);
    }

}
