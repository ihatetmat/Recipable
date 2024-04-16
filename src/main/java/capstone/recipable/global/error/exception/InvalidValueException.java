package capstone.recipable.global.error.exception;

import capstone.recipable.global.error.code.BaseErrorCode;

public class InvalidValueException extends GeneralException {
    private BaseErrorCode baseErrorCode;
    public InvalidValueException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}