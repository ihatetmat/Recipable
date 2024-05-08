package capstone.recipable.domain.user.service;

import capstone.recipable.domain.auth.oauth.dto.CreateUserRequest;
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

    public User createUser(CreateUserRequest request) {
        return User.builder()
                .nickname(request.getName())
                .loginId(request.getEmail())
                .build();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public User findByLoginId(String email) {
        return userRepository.findByLoginId(email);
    }

    public Long save(User user) {
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }
}