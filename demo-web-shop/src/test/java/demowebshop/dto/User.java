package demowebshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class User {
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String confirmPassword;

}
