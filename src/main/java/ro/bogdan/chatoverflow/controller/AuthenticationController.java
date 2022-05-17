package ro.bogdan.chatoverflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.bogdan.chatoverflow.DTO.AuthenticationDTO;
import ro.bogdan.chatoverflow.DTO.LoginDTO;
import ro.bogdan.chatoverflow.DTO.UserDTO;
import ro.bogdan.chatoverflow.exception.InvalidLoginException;
import ro.bogdan.chatoverflow.exception.ValidateUserException;
import ro.bogdan.chatoverflow.service.AuthenticationService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Controller
@RequestMapping("/authentication")
@CrossOrigin()
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping(method = RequestMethod.POST, value ="/registerUser")
    @ResponseBody
    private String registerRegularUser(@RequestBody UserDTO userDTO) {
        String status = "User created";
        try {
            authenticationService.registerRegularUser(userDTO);
        } catch (ValidateUserException validateUserException) {
            status = validateUserException.getMessage();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            status = "Error";
            System.out.println(e.getMessage());
        }
        return status;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    @ResponseBody
    private AuthenticationDTO login(@RequestBody LoginDTO loginDTO) {
        AuthenticationDTO authenticationDTO = new AuthenticationDTO("Something went wrong");
        try {
            authenticationDTO = authenticationService.login(loginDTO);
        } catch (InvalidLoginException invalidLoginException) {
            authenticationDTO = new AuthenticationDTO(invalidLoginException.getMessage());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.out.println(e.getMessage());
            authenticationDTO = new AuthenticationDTO("Something went wrong");
        }
        return authenticationDTO;
    }

}
