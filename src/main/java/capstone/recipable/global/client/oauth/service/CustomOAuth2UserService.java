package capstone.recipable.global.client.oauth.service;

import capstone.recipable.domain.user.dto.UserDTO;
import capstone.recipable.domain.user.entity.User;
import capstone.recipable.domain.user.entity.UserEntity;
import capstone.recipable.domain.user.repository.UserRepository;
import capstone.recipable.global.client.oauth.dto.CustomOAuth2User;
import capstone.recipable.global.client.oauth.dto.OAuth2Response;
import capstone.recipable.global.client.oauth.dto.KakaoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //OAuth2LoginAuthenticationProvider로부터 받은 사용자 정보를 얻기위한 메서드
        OAuth2User oAuth2User = super.loadUser(userRequest);
        //여기에 사용자 정보 담겨있음
        System.out.println(oAuth2User);

        //카카오인지 네이버인지 어디에서 온 요청인지 알기 위한 id 획득
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("kakao")) {
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        }
        else {

            return null;
        }

        //리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디값을 만듬
        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        //UserEntity existData = userRepository.findByUsername(username);
        User existData = userRepository.findByUsername(username);

        if (existData == null) {

            /*UserEntity userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setName(oAuth2Response.getName());
            userEntity.setRole("ROLE_USER");*/
            User user = User.of(username, oAuth2Response.getName(), oAuth2Response.getGender(), oAuth2Response.getBirthyear(), "ROLE_USER");

            userRepository.save(user);

            /*UserDTO userDTO = new UserDTO();
            userDTO.setUsername(username);
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole("ROLE_USER");*/
            UserDTO userDTO = new UserDTO(username, oAuth2Response.getName(), oAuth2Response.getGender(), oAuth2Response.getBirthyear());

            return new CustomOAuth2User(userDTO);
        }
        else {
            existData.setName(oAuth2Response.getName());

            userRepository.save(existData);

            /*UserDTO userDTO = new UserDTO();
            userDTO.setUsername(existData.getUsername());
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole(existData.getRole());*/
            UserDTO userDTO = new UserDTO(username, oAuth2Response.getName(), oAuth2Response.getGender(), oAuth2Response.getBirthyear());

            return new CustomOAuth2User(userDTO);
            //return new CustomOAuth2User(userDTO);
        }
    }

}
