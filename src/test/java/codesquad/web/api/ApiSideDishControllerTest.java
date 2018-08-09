package codesquad.web.api;

import codesquad.domain.SideDish;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.junit.Assert.*;

@Slf4j
public class ApiSideDishControllerTest extends AcceptanceTest {

    @Test
    public void 검색_조회_테스트() {
        String searchText = "김";
        ResponseEntity<SideDish[]> response = template().getForEntity(String.format("/api/side/dishes?query=%s", searchText), SideDish[].class);
        log.debug("response.getBody() : {}", response.getBody());
    }
}