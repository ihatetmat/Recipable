package capstone.recipable.domain.register.dto.request;

import capstone.recipable.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegisterRequest {

    private String nickname;

    private String loginId;

    private String password;

    public User toEntity(RegisterRequest request, String encodedPassword) {
        return User.builder()
                .nickname(request.getNickname())
                .loginId(request.getLoginId())
                .password(encodedPassword)
                .build();
    }
}

