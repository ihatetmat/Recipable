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
import capstone.recipable.global.entity.Uuid;
import capstone.recipable.global.error.ApplicationException;
import capstone.recipable.global.error.ErrorCode;
import capstone.recipable.global.s3.AmazonS3Manager;
import capstone.recipable.global.s3.UuidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RefrigeratorService {
    private final UserRepository userRepository;
    private final RefrigeratorRepository refrigeratorRepository;
    private final CategoryRepository categoryRepository;
    private final IngredientRepository ingredientRepository;
    private final ExpirationRepository expirationRepository;
    private final UuidRepository uuidRepository;
    private final AmazonS3Manager amazonS3Manager;


    public RefrigeratorListResponse getAllIngredientsByRefrigerator() {
        User user = userRepository.findById(SecurityContextProvider.getAuthenticatedUserId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        Refrigerator refrigerator = refrigeratorRepository.findByUser(user).orElse(null);
//                .orElseThrow(() -> new ApplicationException(ErrorCode.REFRIGERATOR_NOT_FOUND));

        if (refrigerator == null) {
            return null;
        }

        List<Category> allCategoryByRefrigerator = categoryRepository.findAllByRefrigerator(refrigerator);
        List<RefrigeratorResponse> refrigeratorResponses = allCategoryByRefrigerator.stream()
                .map(category -> {
                    List<Ingredient> ingredients = ingredientRepository.findAllByCategory(category);

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

        Refrigerator refrigerator = refrigeratorRepository.findByUser(user).orElse(null);
//                .orElseThrow(() -> new ApplicationException(ErrorCode.REFRIGERATOR_NOT_FOUND));

        if (refrigerator == null) {
            return null;
        }

        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INGREDIENT_NOT_FOUND));

        if (!ingredient.getCategory().getRefrigerator().equals(refrigerator)) {
            throw new ApplicationException(ErrorCode.WRONG_USER);
        }

        Expiration expiration = expirationRepository.findByIngredient(ingredient).orElse(null);
//                .orElseThrow(() -> new ApplicationException(ErrorCode.EXPIRATION_NOT_FOUND));

        LocalDate expireDate = expiration != null ? expiration.getExpireDate() : null;

        return IngredientDetailResponse.of(ingredient.getIngredientName(), ingredient.getIngredientImage(), ingredient.getCategory().getCategoryName(),
                expireDate, ingredient.getMemo());
    }

    @Transactional
    public IngredientDetailResponse updateIngredient(Long ingredientId, UpdateIngredientRequest updateIngredientRequest, MultipartFile multipartFile) {
        User user = userRepository.findById(SecurityContextProvider.getAuthenticatedUserId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        Refrigerator refrigerator = refrigeratorRepository.findByUser(user)
                .orElseThrow(() -> new ApplicationException(ErrorCode.REFRIGERATOR_NOT_FOUND));

        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INGREDIENT_NOT_FOUND));

        String ingredientName = ingredient.getIngredientName();
        if (updateIngredientRequest != null && updateIngredientRequest.ingredientName() != null) {
            ingredientName = updateIngredientRequest.ingredientName();
        }

        Category category = ingredient.getCategory();
        if (updateIngredientRequest != null && updateIngredientRequest.categoryName() != null) {
            category = categoryRepository.findByCategoryNameAndRefrigerator(updateIngredientRequest.categoryName(), refrigerator)
                    .orElseThrow(() -> new ApplicationException(ErrorCode.CATEGORY_NOT_FOUND));
        }

        Expiration expiration = expirationRepository.findByIngredient(ingredient)
                .orElseGet(() -> {
                    Expiration newExpiration = Expiration.of(null, updateIngredientRequest.expirationDay(), ingredient);
                    return expirationRepository.save(newExpiration);
                });

        if (updateIngredientRequest != null && updateIngredientRequest.expirationDay() != null) {
            expiration.updateExpirationDate(updateIngredientRequest.expirationDay());
            expirationRepository.save(expiration);
        }

        String memo = ingredient.getMemo();
        if (updateIngredientRequest != null && updateIngredientRequest.memo() != null) {
            memo = updateIngredientRequest.memo();
        }

        if (multipartFile != null) {
                String uuid = UUID.randomUUID().toString();
                Uuid savedUuid = uuidRepository.save(Uuid.builder().uuid(uuid).build());
                String uploadedFile = amazonS3Manager.uploadFile(amazonS3Manager.generateIngredientKeyName(savedUuid), multipartFile);
            ingredient.updateIngredientInfo(ingredientName, uploadedFile, memo, category);
        } else
            ingredient.updateIngredientInfo(ingredientName, ingredient.getIngredientImage(), memo, category);

        return IngredientDetailResponse.of(ingredientName, ingredient.getIngredientImage(), ingredient.getCategory().getCategoryName(),
                expiration.getExpireDate(), memo);
        }

    @Transactional
    public void deleteIngredient(Long ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INGREDIENT_NOT_FOUND));

        ingredientRepository.delete(ingredient);
    }
}
