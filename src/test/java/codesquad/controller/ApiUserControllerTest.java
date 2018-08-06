package codesquad.controller;

import codesquad.dto.UserDto;
import codesquad.error.ErrorResponse;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import support.test.AcceptanceTest;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiUserControllerTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(ApiUserControllerTest.class);
    private Map<String, String> defaultUser = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        defaultUser.put("userId", "serverwizard@naver.com");
        defaultUser.put("password", "test33##");
    }

    @Test
    public void 로그인_성공() throws Exception {
        ResponseEntity<Void> response = template().postForEntity("/api/users/login", defaultUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void 로그인_존재하지않는아이디() throws Exception {
        Map<String, String> newUser = defaultUser;
        newUser.put("userId", "promise");

        ResponseEntity<Void> response = template().postForEntity("/api/users/login", newUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void 로그인_비밀번호틀림() throws Exception {
        Map<String, String> newUser = defaultUser;
        newUser.put("password", "test32##");

        ResponseEntity<ErrorResponse> response = template().postForEntity("/api/users/login", newUser, ErrorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        log.debug("message = {}", response.getBody().getMessage());
    }

    @Test
    public void 회원가입() throws Exception {
        UserDto newUser = new UserDto()
                .setUserId("javajigi@gmail.com")
                .setPassword("testt@est134")
                .setPassword2("testt@est134")
                .setName("재성")
                .setPhoneNumber("010-1111-2222");

        ResponseEntity<Void> response = template().postForEntity("/api/users", newUser, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}