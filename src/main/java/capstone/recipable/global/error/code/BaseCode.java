package capstone.recipable.global.error.code;

import capstone.recipable.global.error.dto.ReasonDto;

public interface BaseCode {
    public ReasonDto getReason();

    public ReasonDto getReasonHttpStatus();
}
