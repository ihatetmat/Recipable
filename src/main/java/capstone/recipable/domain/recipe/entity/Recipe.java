package capstone.recipable.domain.recipe.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Recipe {

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
