package codesquad.service;

import codesquad.domain.Category;
import codesquad.domain.CategoryRepository;
import codesquad.domain.User;
import codesquad.dto.CategoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public void create(CategoryDto categoryDto, Long id, User loginUser) {
        log.debug("params : {}, {}, {}", categoryDto, id, loginUser);
        Category parentCategory = (id == null) ? null : categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 부모 카테고리가 존재하지 않습니다."));
        categoryRepository.save(categoryDto.toCategory(parentCategory, loginUser));

    }
}
