package ro.bogdan.chatoverflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.bogdan.chatoverflow.DTO.AuthenticationDTO;
import ro.bogdan.chatoverflow.DTO.LoginDTO;
import ro.bogdan.chatoverflow.DTO.UserDTO;
import ro.bogdan.chatoverflow.exception.InvalidLoginException;
import ro.bogdan.chatoverflow.exception.ValidateUserException;
import ro.bogdan.chatoverflow.model.User;
import ro.bogdan.chatoverflow.security.PasswordEncoder;
import ro.bogdan.chatoverflow.validator.EmailValidator;
import ro.bogdan.chatoverflow.validator.PasswordValidator;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    public User registerRegularUser(UserDTO userDTO) throws ValidateUserException, NoSuchAlgorithmException, InvalidKeySpecException {
        if(!EmailValidator.isValid(userDTO.getEmail()))
            throw new ValidateUserException("Email is not valid");
        if(!PasswordValidator.passwordMatch(userDTO.getPassword(), userDTO.getPasswordValidation()))
            throw new ValidateUserException("Passwords doesn't match");
        if(!PasswordValidator.isValid(userDTO.getPassword()))
            throw new ValidateUserException("Password is not valid");
        if(userService.userExists(userDTO.getEmail()))
            throw new ValidateUserException("The email is used");
        String passwordHash = PasswordEncoder.createHash(userDTO.getPassword());
        User registered = new User(userDTO.getUsername(), userDTO.getEmail(), passwordHash, roleService.getRole("user"));
        userService.saveUser(registered);
        return userService.getUserByUsername(userDTO.getUsername());
    }

    public AuthenticationDTO login(LoginDTO loginDTO) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidLoginException {
        User user = userService.getUserByUsername(loginDTO.getUsername());
        AuthenticationDTO authenticationDTO;
        if(PasswordEncoder.validatePassword(loginDTO.getPassword(), user.getPasswordHash()))
            authenticationDTO = new AuthenticationDTO(user.getUsername(), user.getRole());
        else
            throw new InvalidLoginException("The combination of username and password doesn't match");
        return authenticationDTO;
    }


}
