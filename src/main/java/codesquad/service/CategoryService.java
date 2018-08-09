package codesquad.service;

import codesquad.domain.Category;
import codesquad.domain.CategoryRepository;
import codesquad.domain.User;
import codesquad.dto.CategoryDto;
import codesquad.exception.UnAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Transactional
    public void create(CategoryDto categoryDto, Long id, User loginUser) throws UnAuthenticationException {
        log.debug("params : {}, {}, {}", categoryDto, id, loginUser);
        Category parentCategory = (id == null) ? null : categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 부모 카테고리가 존재하지 않습니다."));
        categoryRepository.save(categoryDto.toCategory(parentCategory, loginUser));

    }

    public Category update(CategoryDto categoryDto, User loginUser) {
        Category category = categoryRepository.findById(categoryDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당하는 카테고리가 존재하지 않습니다."));
        return category.update(categoryDto, loginUser);
    }

}
