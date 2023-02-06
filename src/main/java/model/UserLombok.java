package model;
import lombok.*;
@Setter
@Getter
@ToString
@Builder
public class UserLombok {
    private String email;
    private String password;
    private String name;
    private String lastName;

}
