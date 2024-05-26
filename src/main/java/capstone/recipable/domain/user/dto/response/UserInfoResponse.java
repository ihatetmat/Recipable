package capstone.recipable.domain.user.dto.response;

import capstone.recipable.domain.user.entity.User;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class UserInfoResponse {

    private String nickname;

    private String userImg;

    public static UserInfoResponse of(User user) {
        return UserInfoResponse.builder()
                .nickname(user.getNickname())
                .userImg(user.getUserImg())
                .build();
    }
}
