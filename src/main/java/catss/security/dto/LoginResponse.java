package catss.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {

  private String username;
  private String token;
  private List<String> roles;

  public LoginResponse(String username, String token, List<String> roles) {
    this.username = username;
    this.token = token;
    this.roles = roles;
  }
}