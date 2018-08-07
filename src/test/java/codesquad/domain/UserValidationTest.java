package codesquad.domain;

import codesquad.dto.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class UserValidationTest {
    private static final Logger log = LoggerFactory.getLogger(UserValidationTest.class);

    private static Validator validator;
    private UserDto defaultUser;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        defaultUser = new UserDto("javajigi@javajigi.com", "1234qwer!", "자바지기", "010-0101-0101", "1234qwer!");
    }

    @Test
    public void 회원가입_정상() {
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(defaultUser);
        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @Test
    public void 회원가입_아이디_비정상() {
        defaultUser.setUserId("javajigi");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(defaultUser);
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void 회원가입_비밀번호_비정상() {
        defaultUser.setPassword("test123");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(defaultUser);
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void 회원가입_이름_비정상() {
        defaultUser.setName("jigi");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(defaultUser);
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void 회원가입_휴대전화번호_비정상() {
        defaultUser.setPhoneNumber("010-123");
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(defaultUser);
        assertThat(constraintViolations.size()).isEqualTo(1);
    }
}