package rs.ac.uns.ftn.webshopservice.mappers;

import rs.ac.uns.ftn.webshopservice.dto.request.UserRegistrationDTO;
import rs.ac.uns.ftn.webshopservice.dto.response.UserDTO;
import rs.ac.uns.ftn.webshopservice.model.Buyer;
import rs.ac.uns.ftn.webshopservice.model.User;

public class UserMapper {

    public static User toUserEntity(UserRegistrationDTO userInfo) {
        User user = new User();
        user.setUsername(userInfo.getUsername());
        user.setEmail(userInfo.getEmail());
        user.setEmail(userInfo.getName());

        return user;
    }

    public static Buyer toBuyerEntity(UserRegistrationDTO userInfo) {
        Buyer user = new Buyer();
        user.setUsername(userInfo.getUsername());
        user.setEmail(userInfo.getEmail());
        user.setName(userInfo.getName());

        return user;
    }

    public static UserDTO toDto(User user) {
        return new UserDTO(user);
    }

    private UserMapper() {
    }
}
