package codesquad.web.api;

import codesquad.domain.Category;
import codesquad.domain.CategoryRepository;
import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.dto.CategoryDto;
import codesquad.exception.UnAuthenticationException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCategoryControllerTest extends AcceptanceTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;
    private Category savedCategory;

    private User normalUser;
    private User adminUser;

    @Before
    public void setUp() throws Exception {
        normalUser = userRepository.findByUserId("serverwizard@naver.com").orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다."));
        adminUser = userRepository.findByUserId("javajigi@slipp.com").orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다."));
//        savedCategory = categoryRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("해당하는 카테고리가 없습니다."));
    }

    // 단위 테스트가 아니면 expected 사용 불가
    @Test
    public void 메인_카테고리_등록_일반_사용자() {
        ResponseEntity<Void> response = basicAuthTemplate(normalUser).postForEntity("/api/categories", new CategoryDto("밑반찬"), Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void 메인_카테고리_등록_관리자() {
        ResponseEntity<Void> response = basicAuthTemplate(adminUser).postForEntity("/api/categories", new CategoryDto("밑반찬"), Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void 카테고리_수정_일반_사용자() {
        ResponseEntity<CategoryDto> response =
                basicAuthTemplate(normalUser).exchange(
                        "/api/categories",
                        HttpMethod.PUT,
                        new HttpEntity<>(new CategoryDto(1L, "밑반찬")), CategoryDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void 카테고리_수정_관리자() {
        CategoryDto updateCategoryDto = new CategoryDto(1L, "국,찌개");
        ResponseEntity<CategoryDto> response =
                basicAuthTemplate(adminUser).exchange(
                        "/api/categories",
                        HttpMethod.PUT,
                        new HttpEntity<>(updateCategoryDto), CategoryDto.class);
        assertThat(response.getBody().getName()).isEqualTo(updateCategoryDto.getName());
    }

}