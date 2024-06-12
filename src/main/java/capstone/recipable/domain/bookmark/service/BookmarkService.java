package capstone.recipable.domain.bookmark.service;

import capstone.recipable.domain.auth.jwt.SecurityContextProvider;
import capstone.recipable.domain.bookmark.entity.Bookmark;
import capstone.recipable.domain.bookmark.repository.BookmarkRepository;
import capstone.recipable.domain.recipe.dto.response.RecipeSummaryResponse;
import capstone.recipable.domain.recipe.entity.Recipe;
import capstone.recipable.domain.recipe.repository.RecipeRepository;
import capstone.recipable.domain.user.entity.User;
import capstone.recipable.domain.user.repository.UserRepository;
import capstone.recipable.global.error.ApplicationException;
import capstone.recipable.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    @Transactional
    public String createBookmark(Long recipeId) {
        Long userId = SecurityContextProvider.getAuthenticatedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.RECIPE_NOT_FOUND));

        Bookmark bookmark = Bookmark.of(user, recipe);
        bookmarkRepository.save(bookmark);

        return "북마크가 되었습니다.";
    }

    public List<RecipeSummaryResponse> getBookmarkList() {
        Long userId = SecurityContextProvider.getAuthenticatedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        List<Bookmark> bookmarks = bookmarkRepository.findAllByUser(user);
        List<RecipeSummaryResponse> response = bookmarks.stream()
                .map(bookmark -> RecipeSummaryResponse.of(bookmark.getRecipe()))
                .collect(Collectors.toList());

        return response;
    }

    @Transactional
    public String deleteBookmark(Long recipeId) {
        Long userId = SecurityContextProvider.getAuthenticatedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.RECIPE_NOT_FOUND));

        Bookmark bookmark = bookmarkRepository.findByUserAndRecipe(user, recipe);

        bookmarkRepository.delete(bookmark);

        return "북마크 취소";
    }
}
