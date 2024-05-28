package capstone.recipable.domain.user.service;

import capstone.recipable.domain.auth.jwt.SecurityContextProvider;
import capstone.recipable.domain.auth.oauth.dto.CreateUserRequest;
import capstone.recipable.domain.recipe.dto.response.RecentRecipeResponse;
import capstone.recipable.domain.recipe.dto.response.TodayRecipeResponse;
import capstone.recipable.domain.recipe.entity.Recipe;
import capstone.recipable.domain.recipe.repository.RecipeRepository;
import capstone.recipable.domain.user.dto.request.UpdateUserInfo;
import capstone.recipable.domain.user.dto.response.MainPageResponse;
import capstone.recipable.domain.user.dto.response.UserInfoResponse;
import capstone.recipable.domain.user.entity.User;
import capstone.recipable.domain.user.repository.UserRepository;
import capstone.recipable.global.error.ApplicationException;
import capstone.recipable.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    public MainPageResponse getMainPage() {
        Long userId = SecurityContextProvider.getAuthenticatedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.ENTITY_NOT_FOUND));

        // 오늘의 레시피 랜덤으로 하나 선택
        Recipe todayRecipe = recipeRepository.findRandomRecipe();
        TodayRecipeResponse todayRecipeResponse = TodayRecipeResponse.of(todayRecipe);

        // 최근 검색 레시피, 레시피 db에서 id 큰순 내림차순으로 세개 가져오기
        List<RecentRecipeResponse> recentRecipeResponses = recipeRepository.recentRecipes(user).stream()
                .map(RecentRecipeResponse::of)
                .collect(Collectors.toList());

        // MainPageResponse에 넣기
        return MainPageResponse.of(todayRecipeResponse, recentRecipeResponses);
    }

    //user 정보 조회 dto화
    public UserInfoResponse getUserInfo() {
        Long userId = SecurityContextProvider.getAuthenticatedUserId();
        User user = findById(userId);
        return UserInfoResponse.of(user);
    }

    //user 정보 수정
    @Transactional
    public UserInfoResponse updateUserInfo(UpdateUserInfo request) {
        Long userId = SecurityContextProvider.getAuthenticatedUserId();
        User user = findById(userId);
        user.update(request.getNickname());
        return UserInfoResponse.of(user);
    }

    //user 삭제
    @Transactional
    public void deleteUser() {
        Long userId = SecurityContextProvider.getAuthenticatedUserId();
        userRepository.deleteById(userId);
    }

    @Transactional
    public User createUser(CreateUserRequest request) {
        return User.builder()
                .nickname(request.getName())
                .loginId(request.getEmail())
                .userImg(request.getImageUrl())
                .build();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    @Transactional
    public Long save(User user) {
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }
}