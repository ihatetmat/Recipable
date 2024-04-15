package capstone.recipable.domain.ingredient;

import capstone.recipable.domain.category.Category;
import capstone.recipable.domain.expiration.Expiration;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ingredientName;

    @ManyToOne(fetch=FetchType.LAZY)
    private Category categoryId;

    @OneToOne
    private Expiration expirationId;
}
