package capstone.recipable.domain.ingredient.api;

import capstone.recipable.domain.ingredient.api.dto.response.IngredientListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class RecipeController {
    private final NaverOcrController naverApi;

    @PostMapping("/naver-ocr")
    public ApiResponse<IngredientListResponse> getIngredientsByOcr(@RequestPart(value = "multipartFile")
                                                                   MultipartFile multipartFile) {

        IngredientListResponse ingredientList = naverApi.callApi(multipartFile, "jpg");
        return ApiResponse.onSuccess(ingredientList);
    }

}
