package codesquad.dto;

import codesquad.domain.Category;
import codesquad.domain.User;
import codesquad.exception.UnAuthenticationException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
    private Long id;
    private String name;

    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDto(String name) {
        this.name = name;
    }

    public static CategoryDto toDto(Category category) {
        return new CategoryDto(category.getName());
    }

    public Category toCategory(Category parentCategory, User loginUser) throws UnAuthenticationException {
        return new Category(this.name, parentCategory, loginUser);
    }
}
