package capstone.recipable.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDTO {

    private String username;

    private String name;

    //test
    private String gender;

    private String birthyear;

    private String role;

    public UserDTO (String nickname, String username, String gender, String birthyear) {
        this.name = nickname;
        this.username = username;
        this.gender = gender;
        this.birthyear = birthyear;
    }

    public UserDTO (String username, String role) {
        this.name = username;
        this.role = role;
    }
}
