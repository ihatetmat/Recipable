package capstone.recipable.domain.refrigerator.service;

import capstone.recipable.domain.auth.jwt.SecurityContextProvider;
import capstone.recipable.domain.category.entity.Category;
import capstone.recipable.domain.category.repository.CategoryRepository;
import capstone.recipable.domain.ingredient.entity.Ingredient;
import capstone.recipable.domain.ingredient.repository.IngredientRepository;
import capstone.recipable.domain.refrigerator.dto.CreateIngredientListRequest;
import capstone.recipable.domain.refrigerator.entity.Refrigerator;
import capstone.recipable.domain.refrigerator.repository.RefrigeratorRepository;
import capstone.recipable.domain.user.entity.User;
import capstone.recipable.domain.user.repository.UserRepository;
import capstone.recipable.global.error.ApplicationException;
import capstone.recipable.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CreateIngredientService {
    private final IngredientRepository ingredientRepository;
    private final RefrigeratorRepository refrigeratorRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;


    @Transactional
    public void createIngredient(CreateIngredientListRequest createIngredientListRequest) {
        User user = userRepository.findById(SecurityContextProvider.getAuthenticatedUserId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        Refrigerator refrigerator = refrigeratorRepository.findByUserId(user)
                .orElseGet(() -> refrigeratorRepository.save(Refrigerator.of(null, user)));

        createIngredientListRequest.ingredients()
                .forEach(ingredientRequest -> {
                    Category category = categoryRepository.findByCategoryNameAndRefrigeratorId(ingredientRequest.categoryName(), refrigerator)
                            .orElseGet(() ->
                                    categoryRepository.save(Category.of(null, ingredientRequest.categoryName(), null, refrigerator))
                            );
                    ingredientRepository.save(
                            Ingredient.of(null, ingredientRequest.ingredientName(), null, null, category)
                    );
                });
    }

}
