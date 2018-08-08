package codesquad.web.api;

import codesquad.domain.Category;
import codesquad.domain.CategoryRepository;
import codesquad.dto.CategoryDto;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCategoryControllerTest extends AcceptanceTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private Category savedCategory;


//    @Before
//    public void setUp() throws Exception {
//        savedCategory = categoryRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("해당하는 카테고리가 없습니다."));
//    }

    @Test
    public void 메인_카테고리_등록() {
        ResponseEntity<Void> response = basicAuthTemplate().postForEntity("/api/categories", new CategoryDto("밑반찬"), Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void 서브_카테고리_등록() {
        ResponseEntity<Void> response = template().postForEntity(String.format("/api/categories/%d", savedCategory.getId()), new CategoryDto("무침"), Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}