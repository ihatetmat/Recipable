package capstone.recipable.domain.expiration.repository;

import capstone.recipable.domain.expiration.entity.Expiration;
import capstone.recipable.domain.ingredient.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpirationRepository extends JpaRepository<Expiration, Long> {
    Optional<Expiration> findByIngredient(Ingredient ingredient);
}
