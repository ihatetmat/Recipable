package capstone.recipable.domain.refrigerator.dto.response;

import lombok.Builder;

@Builder
public record RefrigeratorDetailResponse(
        String ingredientName,
        Long expiredRemaining,
        String ingredientImage
) {
    public static RefrigeratorDetailResponse of(String ingredientName, Long expiredRemaining,String ingredientImage) {
        return new RefrigeratorDetailResponse(ingredientName, expiredRemaining, ingredientImage);
    }
}
