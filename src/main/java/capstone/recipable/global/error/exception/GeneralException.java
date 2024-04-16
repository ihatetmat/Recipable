package capstone.recipable.global.error.exception;

import capstone.recipable.global.error.code.BaseErrorCode;
import capstone.recipable.global.error.dto.ErrorReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private BaseErrorCode baseErrorCode;

    public ErrorReasonDto getErrorReason() {
        return this.baseErrorCode.getReason();
    }

    public ErrorReasonDto getErrorReasonHttpStatus() {
        return this.baseErrorCode.getReasonHttpStatus();
    }
}
