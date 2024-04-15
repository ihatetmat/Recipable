package capstone.recipable.global.storage.ingredient.jpa;

import capstone.recipable.global.storage.category.jpa.CategoryEntity;
import capstone.recipable.global.storage.expiration.jpa.ExpirationEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ingredientName;

    @ManyToOne(fetch=FetchType.LAZY)
    private CategoryEntity categoryId;

    @OneToOne
    private ExpirationEntity expirationId;
}
