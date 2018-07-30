package codesquad.web;


import codesquad.domain.Category;
import codesquad.dto.CategoryDto;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCategoryControllerTest extends AcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(ApiCategoryControllerTest.class);

    @Test
    public void 메인_카테고리_등록_성공() {
        CategoryDto categoryDto = new CategoryDto("아이반찬");
        ResponseEntity<Category> response = template().postForEntity("/api/categories", categoryDto, Category.class);
        assertThat(response.getBody().getName()).isEqualTo("아이반찬");
    }

    @Test
    public void 서브_카테고리_등록_성공() {
        CategoryDto subCategoryDto = new CategoryDto("조림");
        ResponseEntity<Category> response = template().postForEntity("/api/categories/1", subCategoryDto, Category.class);
        assertThat(response.getBody().getName()).isEqualTo("조림");
    }

    @Test
    public void 카테고리_조회() {
        ResponseEntity<Category[]> response = template().getForEntity("/api/categories", Category[].class);
        log.debug("response.getBody : {}", response.getBody());
    }
}