package capstone.recipable.domain.user.service;

import capstone.recipable.domain.auth.jwt.SecurityContextProvider;
import capstone.recipable.domain.auth.oauth.dto.CreateUserRequest;
import capstone.recipable.domain.user.dto.request.UpdateUserInfo;
import capstone.recipable.domain.user.dto.response.UserInfoResponse;
import capstone.recipable.domain.user.entity.User;
import capstone.recipable.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    //user 정보 조회 dto화
    public UserInfoResponse getUserInfo() {
        Long userId = SecurityContextProvider.getAuthenticatedUserId();
        User user = findById(userId);
        return UserInfoResponse.of(user);
    }

    //user 정보 수정
    public UserInfoResponse updateUserInfo(UpdateUserInfo request) {
        Long userId = SecurityContextProvider.getAuthenticatedUserId();
        User user = findById(userId);
        user.update(request.getNickname());
        return UserInfoResponse.of(user);
    }

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

    public Long save(User user) {
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }
}