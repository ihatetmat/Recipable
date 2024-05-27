package capstone.recipable.domain.refrigerator.dto.response;

public record RefrigeratorDetailResponse(
        String ingredientName,
        Long expiredRemaining,
        String ingredientImage
) {
    public static RefrigeratorDetailResponse of(String ingredientName, Long expiredRemaining,String ingredientImage) {
        return new RefrigeratorDetailResponse(ingredientName, expiredRemaining, ingredientImage);
    }
}
