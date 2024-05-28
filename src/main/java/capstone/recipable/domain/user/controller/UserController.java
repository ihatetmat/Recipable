package capstone.recipable.domain.user.controller;

import capstone.recipable.domain.user.dto.request.UpdateUserInfo;
import capstone.recipable.domain.user.dto.response.UserInfoResponse;
import capstone.recipable.domain.user.service.UserService;
import capstone.recipable.global.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Tag(name = "user", description = "user 관련 api")
public class UserController {

    private final UserService userService;

    /*@GetMapping("/main")
    public ResponseEntity<SuccessResponse<>>*/

    @Operation(summary = "사용자 정보 조회")
    @GetMapping("/info")
    public ResponseEntity<SuccessResponse<UserInfoResponse>> getUserInfo() {
        UserInfoResponse response = userService.getUserInfo();
        return SuccessResponse.of(response);
    }

    @Operation(summary = "사용자 정보 수정", description = """
            사용자 정보 수정 api입니다.
            
            변경하고 싶은 내용을 입력하여 RquestBody에 보내주세요.
            
            현재 변경가능한 항목은 nickname입니다.
            """)
    @PutMapping("/info")
    public ResponseEntity<SuccessResponse<UserInfoResponse>> updateUserInfo(@RequestBody UpdateUserInfo request) {
        UserInfoResponse response = userService.updateUserInfo(request);
        return SuccessResponse.of(response);
    }

    @Operation(summary = "사용자 정보 삭제", description = """
            사용자 정보 삭제 api입니다.
            
            회원탈퇴 시 호출하면 됩니다.
            
            회원탈퇴가 정상적으로 처리될 경우 회원 탈퇴 성공 메시지를 반환합니다.
            """)
    @DeleteMapping("/info")
    public ResponseEntity<SuccessResponse<String>> deleteUser() {
        userService.deleteUser();
        return SuccessResponse.of("회원 탈퇴 성공");
    }
}
