package codesquad.security;

import codesquad.domain.User;
import codesquad.dto.UserDto;
import codesquad.exception.UnAuthenticationException;
import codesquad.service.UserService;
import codesquad.utils.HttpSessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.Base64;

@Slf4j
public class BasicAuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.debug("BasicAuthInterceptor");
        String authorization = request.getHeader("Authorization");
        log.debug("Authorization : {}", authorization);
        if (authorization == null || !authorization.startsWith("Basic")) {
            return true;
        }

        String base64Credentials = authorization.substring("Basic".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
        final String[] values = credentials.split(":", 2);
        log.debug("username : {}", values[0]);
        log.debug("password : {}", values[1]);

        try {
            User user = userService.login(new UserDto(values[0], values[1]));
            log.debug("Login Success : {}", user);
            request.getSession().setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
            return true;
        } catch (UnAuthenticationException e) {
            return true;
        }
    }
}
