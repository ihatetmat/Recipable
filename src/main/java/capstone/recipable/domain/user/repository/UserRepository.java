package capstone.recipable.domain.user.repository;

import capstone.recipable.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByKakaoId(String kakaoId);
}
