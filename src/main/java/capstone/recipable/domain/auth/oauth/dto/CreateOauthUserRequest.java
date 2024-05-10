package capstone.recipable.domain.auth.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateOauthUserRequest {
    private String name;
    private String email;
    private String gender;
    private String birthyear;
    private String profileImage;
}