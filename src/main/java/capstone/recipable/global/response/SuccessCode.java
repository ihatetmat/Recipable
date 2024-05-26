package capstone.recipable.global.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {
    /**
     * 200 OK
     */
    OK(HttpStatus.OK, "요청이 성공했습니다."),

    /**
     * 201 CREATED SUCCESS
     */
    CREATED(HttpStatus.CREATED, "생성 요청이 성공했습니다.");

    private final HttpStatus status;
    private final String message;

    public int getStatusCode() {
        return status.value();
    }
}
