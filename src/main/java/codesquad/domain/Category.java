package codesquad.domain;

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
    private Category parent;

    @OneToMany(mappedBy = "id")
    private List<Category> subCategories;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="fk_category_writer"))
    private User writer;

    @Column
    private LocalDateTime createTime;

    public Category(String name, Category parentCategory, User loginUser) {
        this.name = name;
        this.parent = parentCategory;
        this.createTime = LocalDateTime.now();
        this.writer = loginUser;
    }
}

// id | name | parent_id | writer_id

// category_id | subcategory_id
