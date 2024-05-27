package capstone.recipable.domain.refrigerator.repository;

import capstone.recipable.domain.refrigerator.entity.Refrigerator;
import capstone.recipable.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Long> {
    Optional<Refrigerator> findByUserId(User user);
}
