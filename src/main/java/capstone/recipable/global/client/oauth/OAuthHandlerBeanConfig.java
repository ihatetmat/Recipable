/*
package capstone.recipable.global.client.oauth;

import capstone.recipable.domain.user.domain.Provider;
import capstone.recipable.domain.user.domain.oauth.OAuthHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class OAuthHandlerBeanConfig {

    private final KakaoOAuthHandler kakaoOAuthHandler;

    @Bean
    public Map<Provider, OAuthHandler> oAuthHandlerMap(){
        return Map.of(
                Provider.KAKAO, kakaoOAuthHandler
        );
    }
}*/
