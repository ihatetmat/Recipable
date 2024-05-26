package capstone.recipable.domain.auth.jwt;

import capstone.recipable.global.error.ApplicationException;
import capstone.recipable.global.error.ErrorCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class SecurityContextProvider {
    public static Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (Objects.isNull(principal)) {
            throw new ApplicationException(ErrorCode.UNAUTHORIZED_USER);
        }
        return (Long) principal;
    }

    public static boolean isAnonymousUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ANONYMOUS"));
    }

    public static void setupSecurityContextForTest(Long userId) {
        UserAuthentication authentication = new UserAuthentication(userId, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
