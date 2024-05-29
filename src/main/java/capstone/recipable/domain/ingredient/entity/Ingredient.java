package capstone.recipable.domain.ingredient.entity;

import capstone.recipable.domain.category.entity.Category;
import capstone.recipable.domain.expiration.entity.Expiration;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ingredientName;

    private String ingredientImage;

    private String memo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(mappedBy = "ingredient", cascade = CascadeType.ALL)
    private Expiration expiration;

    public static Ingredient of(Long id, String ingredientName, String ingredientImage, String memo, Category category, Expiration expiration) {
        return Ingredient.builder()
                .id(id)
                .ingredientName(ingredientName)
                .ingredientImage(ingredientImage)
                .category(category)
                .expiration(expiration)
                .memo(memo)
                .build();
    }

    public void updateIngredientInfo(String ingredientName, String ingredientImage, String memo, Category category) {
        this.ingredientName = ingredientName;
        this.ingredientImage = ingredientImage;
        this.memo = memo;
        this.category = category;
    }

}
