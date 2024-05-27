package capstone.recipable.domain.expiration.entity;

import capstone.recipable.domain.ingredient.entity.Ingredient;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Expiration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate expireDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Ingredient ingredientId;

    public static Expiration of(Long id, LocalDate expireDate, Ingredient ingredientId) {
        return Expiration.builder()
                .id(id)
                .expireDate(expireDate)
                .ingredientId(ingredientId)
                .build();
    }

    public void updateExpirationDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }
}
