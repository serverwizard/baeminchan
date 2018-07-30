package codesquad.interceptor;

import codesquad.domain.Authority;
import codesquad.domain.User;
import codesquad.security.HttpSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        User loginUser = (User) session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
        if (loginUser == null) {
            response.sendRedirect("/login");
            return false;
        }
        if (Authority.ADMIN != loginUser.getRole().getAuthority()) {
            request.getRequestDispatcher("/errors").forward(request, response);
            return false;
        }

        return true;
    }
}