package capstone.recipable.domain.ingredient.entity;

import capstone.recipable.domain.category.entity.Category;
import capstone.recipable.domain.expiration.entity.Expiration;
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
