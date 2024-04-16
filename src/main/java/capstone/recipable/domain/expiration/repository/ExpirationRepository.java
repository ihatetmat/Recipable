package capstone.recipable.domain.expiration.repository;

import capstone.recipable.domain.expiration.entity.Expiration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpirationRepository extends JpaRepository<Expiration, Long> {
}
