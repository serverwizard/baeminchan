package codesquad.web.api;

import codesquad.domain.User;
import codesquad.dto.CategoryDto;
import codesquad.security.LoginUser;
import codesquad.service.CategoryService;
import codesquad.utils.HttpSessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/api/categories")
public class ApiCategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping({"", "/{id}"})
    public ResponseEntity<Void> create(@RequestBody CategoryDto categoryDto, @PathVariable(required = false) Long id, @LoginUser User loginUser) {
        log.debug("loginUser : {}", loginUser);
        categoryService.create(categoryDto, id, loginUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
