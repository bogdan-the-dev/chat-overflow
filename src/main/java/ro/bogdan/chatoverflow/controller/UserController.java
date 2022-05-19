package ro.bogdan.chatoverflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.bogdan.chatoverflow.model.User;
import ro.bogdan.chatoverflow.service.UserService;

import java.util.List;

@CrossOrigin()
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/all-users")
    @ResponseBody
    private List<User> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    @ResponseBody
    private User findUser(@RequestParam(name = "name") String name) {
        return userService.getUserByUsername(name);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find-user")
    @ResponseBody
    private User getUserById(@RequestParam(name = "id") Integer id) {
        return userService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete-user")
    @ResponseBody
    private String deleteUser(@RequestParam(name = "id") Integer id) {
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Delete successful";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/ban-user")
    @ResponseBody
    private String banUser(@RequestParam(name = "username") String username) {
        if(this.userService.banUser(username)) {
            System.out.println("USer \"" + username + "\" has been banned");
            return "User banned";
        }
        else
            return "Something went wrong";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create-user")
    @ResponseBody
    private User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/edit-user")
    @ResponseBody
    private User editUser(@RequestBody User user) {
        return userService.editUser(user);
    }

}
