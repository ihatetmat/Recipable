package capstone.recipable.domain.refrigerator.dto.response;

public record RefrigeratorDetailResponse(
        Long ingredientId,
        String ingredientName,
        Long expiredRemaining,
        String ingredientImage
) {
    public static RefrigeratorDetailResponse of(Long ingredientId,String ingredientName, Long expiredRemaining,String ingredientImage) {
        return new RefrigeratorDetailResponse(ingredientId, ingredientName, expiredRemaining, ingredientImage);
    }
}
