package capstone.recipable.domain.refrigerator.service;

import capstone.recipable.domain.auth.jwt.SecurityContextProvider;
import capstone.recipable.domain.category.entity.Category;
import capstone.recipable.domain.category.repository.CategoryRepository;
import capstone.recipable.domain.expiration.entity.Expiration;
import capstone.recipable.domain.expiration.repository.ExpirationRepository;
import capstone.recipable.domain.ingredient.controller.dto.request.UpdateIngredientRequest;
import capstone.recipable.domain.ingredient.controller.dto.response.IngredientDetailResponse;
import capstone.recipable.domain.ingredient.entity.Ingredient;
import capstone.recipable.domain.ingredient.repository.IngredientRepository;
import capstone.recipable.domain.refrigerator.dto.response.RefrigeratorDetailResponse;
import capstone.recipable.domain.refrigerator.dto.response.RefrigeratorListResponse;
import capstone.recipable.domain.refrigerator.dto.response.RefrigeratorResponse;
import capstone.recipable.domain.refrigerator.entity.Refrigerator;
import capstone.recipable.domain.refrigerator.repository.RefrigeratorRepository;
import capstone.recipable.domain.user.entity.User;
import capstone.recipable.domain.user.repository.UserRepository;
import capstone.recipable.global.error.ApplicationException;
import capstone.recipable.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RefrigeratorService {
    private final UserRepository userRepository;
    private final RefrigeratorRepository refrigeratorRepository;
    private final CategoryRepository categoryRepository;
    private final IngredientRepository ingredientRepository;
    private final ExpirationRepository expirationRepository;


    public RefrigeratorListResponse getAllIngredientsByRefrigerator() {
        User user = userRepository.findById(SecurityContextProvider.getAuthenticatedUserId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        Refrigerator refrigerator = refrigeratorRepository.findByUserId(user).orElse(null);
//                .orElseThrow(() -> new ApplicationException(ErrorCode.REFRIGERATOR_NOT_FOUND));

        if (refrigerator == null) {
            return null;
        }
        List<Category> allCategoryByRefrigerator = categoryRepository.findAllByRefrigeratorId(refrigerator);

        List<RefrigeratorResponse> refrigeratorResponses = allCategoryByRefrigerator.stream()
                .map(category -> {
                    List<Ingredient> ingredients = ingredientRepository.findAllByCategoryId(category);

                    List<RefrigeratorDetailResponse> refrigeratorDetails = ingredients.stream()
                            .map(ingredient -> {
                                Expiration expiration = expirationRepository.findByIngredient(ingredient).orElse(null);

                                Long remainingExpiration = expiration != null ? ChronoUnit.DAYS.between(LocalDate.now(), expiration.getExpireDate()) : null;
                                RefrigeratorDetailResponse refrigeratorDetail = RefrigeratorDetailResponse.of(ingredient.getId(), ingredient.getIngredientName(), remainingExpiration, ingredient.getIngredientImage());

                                return refrigeratorDetail;
                            }).toList();
                    return RefrigeratorResponse.of(category.getCategoryName(), category.getDetails(), refrigeratorDetails);
                }).toList();

        return RefrigeratorListResponse.of(refrigeratorResponses);
    }

    public IngredientDetailResponse getIngredient(Long ingredientId) {
        User user = userRepository.findById(SecurityContextProvider.getAuthenticatedUserId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        Refrigerator refrigerator = refrigeratorRepository.findByUserId(user).orElse(null);
//                .orElseThrow(() -> new ApplicationException(ErrorCode.REFRIGERATOR_NOT_FOUND));

        if (refrigerator == null) {
            return null;
        }

        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INGREDIENT_NOT_FOUND));

        if (!ingredient.getCategoryId().getRefrigeratorId().equals(refrigerator)) {
            throw new ApplicationException(ErrorCode.WRONG_USER);
        }

        Expiration expiration = expirationRepository.findByIngredient(ingredient)
                .orElseThrow(() -> new ApplicationException(ErrorCode.EXPIRATION_NOT_FOUND));

        return IngredientDetailResponse.of(ingredient.getIngredientName(), ingredient.getCategoryId().getCategoryName(),
                expiration.getExpireDate(), ingredient.getMemo());
    }

    @Transactional
    public IngredientDetailResponse updateIngredient(Long ingredientId, UpdateIngredientRequest updateIngredientRequest) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INGREDIENT_NOT_FOUND));

        Expiration expiration = expirationRepository.findByIngredient(ingredient)
                .orElseThrow(() -> new ApplicationException(ErrorCode.EXPIRATION_NOT_FOUND));

        String requestedCategoryName = updateIngredientRequest.categoryName();
        Category category = categoryRepository.findByCategoryName(requestedCategoryName)
                .orElseThrow(() -> new ApplicationException(ErrorCode.CATEGORY_NOT_FOUND));

        ingredient.updateIngredientInfo(updateIngredientRequest.ingredientName(), updateIngredientRequest.ingredientImage(), updateIngredientRequest.memo(), category);
        expiration.updateExpirationDate(updateIngredientRequest.expirationDay());

        return IngredientDetailResponse.of(ingredient.getIngredientName(), ingredient.getCategoryId().getCategoryName(),
                expiration.getExpireDate(), ingredient.getMemo());
    }

    @Transactional
    public void deleteIngredient(Long ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INGREDIENT_NOT_FOUND));

        ingredientRepository.delete(ingredient);
    }
}
