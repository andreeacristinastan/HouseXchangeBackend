package backend.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import backend.exceptions.ForbiddenException;
import backend.exceptions.UnauthorizedException;
import backend.security.AuthUtil;

@AllArgsConstructor
@Component
public class CheckPermissionsHelper {

    public void checkAuth(String email, AuthUtil authUtil) {
        if (authUtil.isAuthenticated() == null || !authUtil.isAuthenticated().equals(email)) {
            throw new UnauthorizedException("You're unauthorized for this action'");
        }
    }

    public void checkUser(String role, AuthUtil authUtil) {
        if (!authUtil.isAuthorized(role)) {
            throw new ForbiddenException("You're access is forbidden for this action");
        }
    }
}
