package capstone.recipable.domain.refrigerator.dto.response;

import java.util.List;

public record RefrigeratorResponse(
        String categoryName,
        String detailContent,
        List<RefrigeratorDetailResponse> refrigeratorDetailList

) {
    public static RefrigeratorResponse of(String categoryName, String detailContent, List<RefrigeratorDetailResponse> refrigeratorDetailResponseList) {
        return new RefrigeratorResponse(categoryName, detailContent, refrigeratorDetailResponseList);
    }
}
