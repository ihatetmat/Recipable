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
    FILE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "S3 이미지 업로드에 실패"),

    /**
     * User 관련 에러
     */
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"사용자를 찾을 수 없습니다."),

    /**
     * Refrigerator 관련 ERROR
     */
    REFRIGERATOR_NOT_FOUND(HttpStatus.NOT_FOUND,"냉장고를 찾을 수 없습니다."),
    WRONG_USER(HttpStatus.BAD_REQUEST,"사용자는 해당 식재료 혹은 냉장고를 가지고 있지 않습니다."),

    /**
     * Ingredient 관련 ERROR
     */
    INGREDIENT_NOT_FOUND(HttpStatus.NOT_FOUND,"식재료를 찾을 수 없습니다."),

    /**
     * EXPIRATION 관련 ERROR
     */
    EXPIRATION_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 식재료의 유효기간을 찾을 수 없습니다."),

    /**
     * CATEGORY 관련 ERROR
     */
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND,"카태고리를 찾을 수 없습니다."),

    /**
     * IMAGE 관련 ERROR
     */
    IMAGE_IS_NOT_UPLOADED(HttpStatus.BAD_REQUEST, "이미지를 정상적으로 업로드하지 못하였습니다.");



    private final HttpStatus status;
    private final String message;
}

