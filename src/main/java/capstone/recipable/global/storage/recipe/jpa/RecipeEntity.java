package capstone.recipable.global.storage.recipe.jpa;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipeImg;

    private String recipeName;

    private String introduce;

    @ElementCollection
    private List<String> ingredients;

    private String recipeDetails;

    @ElementCollection
    private List<String> videoUrls;
}
