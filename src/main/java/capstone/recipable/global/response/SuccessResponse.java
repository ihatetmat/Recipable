package capstone.recipable.global.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)//생성자를 통해서 값 변경 목적으로 접근하는 메시지들 차단
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SuccessResponse<T> {
    private final int status;
    private final String message;
    private T data;

    public static ResponseEntity<SuccessResponse> of(SuccessCode success) {
        return ResponseEntity.status(success.getStatus())
                .body(new SuccessResponse(success.getStatusCode(), success.getMessage()));
    }


    public static <T> ResponseEntity<SuccessResponse<T>> of(SuccessCode success, T data) {
        return ResponseEntity.status(success.getStatus())
                .body(new SuccessResponse<T>(success.getStatusCode(), success.getMessage(), data));
    }


    public static <T> ResponseEntity<SuccessResponse<T>> of(T data) {
        return ResponseEntity.status(SuccessCode.OK.getStatus())
                .body(new SuccessResponse<T>(SuccessCode.OK.getStatusCode(), SuccessCode.OK.getMessage(), data));
    }
}
