package codesquad.domain;

import codesquad.dto.CategoryDto;
import codesquad.exception.UnAuthenticationException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


// TODO mappedby와 자동으로 sub category 테이블 생성?
@Getter
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_category_parent"))
    private Category parentCategory;

    @OneToMany(mappedBy = "id")
    private List<Category> subCategories;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="fk_category_writer"))
    private User writer;

    @Column
    private LocalDateTime createTime;

    public Category(String name, Category parentCategory, User loginUser) throws UnAuthenticationException {
        if(!loginUser.isAdmin()) {
            throw new UnAuthenticationException("관리자 권한만 카테고리 등록이 가능합니다.");
        }
        this.name = name;
        this.parentCategory = parentCategory;
        this.createTime = LocalDateTime.now();
        this.writer = loginUser;
    }

    public Category update(CategoryDto categoryDto, User loginUser) {
        if(!loginUser.isAdmin()) {
            throw new UnAuthenticationException("관리자 권한만 카테고리 수정이 가능합니다.");
        }
        this.name = categoryDto.getName();
        return this;
    }
}

// id | name | parent_id | writer_id

// category_id | subcategory_id
