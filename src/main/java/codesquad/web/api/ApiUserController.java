package codesquad.web.api;

import codesquad.domain.User;
import codesquad.dto.UserDto;
import codesquad.exception.NotMatchException;
import codesquad.exception.UnAuthenticationException;
import codesquad.security.HttpSessionUtils;
import codesquad.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody UserDto userDto, HttpSession session) throws UnAuthenticationException {
        session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, userService.login(userDto));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("")
    public ResponseEntity<User> create(@Valid @RequestBody UserDto userDto) throws NotMatchException {
        userService.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}