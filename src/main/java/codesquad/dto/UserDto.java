package codesquad.dto;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class UserDto {
    private String userId;
    private String password;

    public UserDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
