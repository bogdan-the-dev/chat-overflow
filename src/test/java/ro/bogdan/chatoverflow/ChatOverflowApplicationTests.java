package ro.bogdan.chatoverflow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ro.bogdan.chatoverflow.model.Role;
import ro.bogdan.chatoverflow.model.User;
import ro.bogdan.chatoverflow.service.RoleService;
import ro.bogdan.chatoverflow.service.UserService;

import java.util.Objects;

@SpringBootTest
class ChatOverflowApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Test
    void testUsersExists() {
        assert !userService.getUsers().isEmpty();
    }

    @Test
    void testCreateUser() {
        Role role = roleService.getRoles().get(0);
        User user = new User("user for unit test", "email@gmail.com", "gsdgsdfsdf", false, false, false, false, 0, role);
        userService.saveUser(user);
        User foundUser = userService.getUserById(user.getUserId());
        assert foundUser.getUserId() == user.getUserId();
        assert foundUser.getUsername().equals(user.getUsername());
        assert foundUser.getScore() == 0;
        assert foundUser.getEmail().equals(user.getEmail());
    }

    @Test
    void editUser() {
        User user1 = userService.getUserByUsername("user for unit test");
        boolean userCreated = false;
        if (user1 == null) {
            Role role = roleService.getRoles().get(0);
            User user = new User("user for unit test", "email@gmail.com", "gsdgsdfsdf", false, false, false, false, 0, role);
            userService.saveUser(user);
            userCreated = true;
        }
        user1 = userService.getUserByUsername("user for unit test");
        int userId = user1.getUserId();
        user1.setAccountVerified(true);
        user1.setScore(100);
        userService.saveUser(user1);
        assert userService.getUserById(userId).isAccountVerified();
        assert userService.getUserById(userId).getScore() == 100;
        if(userCreated) {
            try{
                userService.deleteUser(userId);
            } catch (Exception e) {
                assert false;
            }
        }
    }

    @Test
    void testDeleteUser() {
        User user = userService.getUserByUsername("user for unit test");
        try {
            userService.deleteUser(user.getUserId());
        } catch (Exception e) {
            assert  false;
        }
    }
}
