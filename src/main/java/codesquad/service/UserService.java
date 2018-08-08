package codesquad.service;

import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.dto.UserDto;
import codesquad.exception.UnAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User login(UserDto userDto) throws UnAuthenticationException {
        log.debug("param : {}", userDto);
        User savedUser = userRepository.findByUserId(userDto.getUserId()).orElseThrow(()-> new IllegalArgumentException("해당하는 사용자가 없습니다."));
        savedUser.matchBy(passwordEncoder, userDto.getPassword());
        return savedUser;
    }
}
