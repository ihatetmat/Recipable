package capstone.recipable.domain.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String loginId;

    private String password;

    private String userImg;

    //test
    private String kakaoId;

    private String gender;

    private String birthyear;

    private String role;

    public static User ofKakao(String kakaoId, String nickname, String gender, String birthyear, String role) {
        return User.builder()
                .kakaoId(kakaoId)
                .nickname(nickname)
                .gender(gender)
                .birthyear(birthyear)
                .role(role)
                .build();
    }

    public User setName (String nickname) {
        return User.builder()
                .nickname(nickname)
                .build();
    }
}
