package capstone.recipable.domain.refrigerator.dto.response;

import java.util.List;

public record RefrigeratorListResponse(
        List<RefrigeratorResponse> refrigeratorList
) {
    public static RefrigeratorListResponse of(List<RefrigeratorResponse> refrigeratorResponseList) {
        return new RefrigeratorListResponse(refrigeratorResponseList);
    }
}
