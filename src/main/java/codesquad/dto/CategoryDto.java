package codesquad.dto;

import codesquad.domain.Category;
import codesquad.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
    private String name;

    public CategoryDto(String name) {
        this.name = name;
    }

    public Category toCategory(Category parentCategory, User loginUser) {
        return new Category(this.name, parentCategory, loginUser);
    }
}
