package capstone.recipable.global.storage.bookmark.jpa;

import capstone.recipable.global.storage.recipe.jpa.RecipeEntity;
import capstone.recipable.global.storage.user.jpa.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BookmarkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity userId;

    @OneToOne
    private RecipeEntity recipeId;

}
