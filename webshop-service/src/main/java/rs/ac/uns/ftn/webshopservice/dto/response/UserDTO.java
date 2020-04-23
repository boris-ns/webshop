package rs.ac.uns.ftn.webshopservice.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.webshopservice.model.Authority;
import rs.ac.uns.ftn.webshopservice.model.User;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;
    private String username;
    private String name;
    private String email;
    private boolean enabled;
    private List<String> authorities;
    private UserTokenDTO token;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.email = user.getEmail();
        this.enabled = user.isEnabled();
        this.token = null;

        this.authorities = user.getAuthorities().stream()
                .map(authority -> ((Authority) authority).getName()).collect(Collectors.toList());
    }

}
