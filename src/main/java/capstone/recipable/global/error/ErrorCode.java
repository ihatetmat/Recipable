package capstone.recipable.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /**
     *  400 Bad Request
     */
    INVALID_BODY(HttpStatus.NOT_FOUND, "잘못된 요청입니다."),

    /**
     *  401 Unauthorized
     */
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "권한이 없는 사용자입니다."),

    /**
     *  403 Forbidden
     */


    /**
     *  404 Not Found
     */
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "요청하신 엔티티를 찾을 수 없습니다."),
    RECIPE_NOT_FOUND(HttpStatus.NOT_FOUND, "레시피를 찾을 수 없습니다."),

    /**
     * 409 Conflict
     */
    DUPLICATE_SAMPLE_TEXT(HttpStatus.CONFLICT, "이미 존재하는 TEXT입니다."),

    /**
     *  500 INTERNAL SERVER ERROR
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),
    FILE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "S3 이미지 업로드에 실패");

    private final HttpStatus status;
    private final String message;
}

