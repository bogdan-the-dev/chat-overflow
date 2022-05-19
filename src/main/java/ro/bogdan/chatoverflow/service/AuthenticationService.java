package ro.bogdan.chatoverflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.bogdan.chatoverflow.DTO.AuthenticationDTO;
import ro.bogdan.chatoverflow.DTO.LoginDTO;
import ro.bogdan.chatoverflow.DTO.RegisterDTO;
import ro.bogdan.chatoverflow.exception.InvalidLoginException;
import ro.bogdan.chatoverflow.exception.UserIsBannedException;
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


    public User registerRegularUser(RegisterDTO registerDTO) throws ValidateUserException, NoSuchAlgorithmException, InvalidKeySpecException {
        if(!EmailValidator.isValid(registerDTO.getEmail()))
            throw new ValidateUserException("Email is not valid");
        if(!PasswordValidator.passwordMatch(registerDTO.getPassword(), registerDTO.getPasswordValidation()))
            throw new ValidateUserException("Passwords doesn't match");
        if(!PasswordValidator.isValid(registerDTO.getPassword()))
            throw new ValidateUserException("Password is not valid");
        if(userService.userExists(registerDTO.getEmail()))
            throw new ValidateUserException("The email is used");
        String passwordHash = PasswordEncoder.createHash(registerDTO.getPassword());
        User registered = new User(registerDTO.getUsername(), registerDTO.getEmail(), passwordHash, roleService.getRole("user"));
        userService.saveUser(registered);
        return userService.getUserByUsername(registerDTO.getUsername());
    }

    public AuthenticationDTO login(LoginDTO loginDTO) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidLoginException {
        User user = userService.getUserByUsername(loginDTO.getUsername());
        if(user == null)
            throw new InvalidLoginException();
        if(user.isAccountBanned())
            throw new UserIsBannedException();
        AuthenticationDTO authenticationDTO;
        if(PasswordEncoder.validatePassword(loginDTO.getPassword(), user.getPasswordHash()))
            authenticationDTO = new AuthenticationDTO(user.getUserId(),user.getUsername(), user.getRole());
        else
            throw new InvalidLoginException();
        return authenticationDTO;
    }


}
