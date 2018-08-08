package codesquad.domain;

import codesquad.exception.UnAuthenticationException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
@Entity
public class User {
    public static final GuestUser GUEST_USER = new GuestUser();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true, updatable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_user_to_role"))
    private Role role;

    @Column
    private LocalDateTime joinDateTime;

    public void matchBy(PasswordEncoder passwordEncoder, String password) throws UnAuthenticationException {
        if(!passwordEncoder.matches(password, this.password)) {
            throw new UnAuthenticationException("비밀번호가 일치하지 않습니다.");
        }
    }

    @JsonIgnore
    public boolean isGuestUser() {
        return false;
    }

    private static class GuestUser extends User {
        @Override
        public boolean isGuestUser() {
            return true;
        }
    }
}

// id | user_id | password | name | phone_number | role_id | join_date_time
// TODO @ForeignKey(name = "fk_user_to_role") 이름 짓는법