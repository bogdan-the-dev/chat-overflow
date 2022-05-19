package ro.bogdan.chatoverflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.bogdan.chatoverflow.model.User;
import ro.bogdan.chatoverflow.repository.IUserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private EmailServiceImpl emailService;

    public List<User> getUsers() {
        return (List<User>) iUserRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return iUserRepository.findUserByUsernameIs(username).orElse(null);
    }

    public boolean userExists(String email) {
        Optional<User> userOptional = iUserRepository.findUserByEmailIsIgnoreCase(email);
        User user = userOptional.orElse(null);
        return !(user == null);
    }

    public User getUserById(Integer id) {
        return iUserRepository.findById(id).orElse(null);
    }

    public void deleteUser(Integer id) throws Exception {
        try {
            iUserRepository.delete(this.getUserById(id));
        } catch (Exception e) {
            throw new Exception("Delete failed, user not found");
        }
    }

    public User editUser(User user) {
        User editedUser = this.iUserRepository.getById(user.getUserId());
        editedUser.setTwoFA(user.isTwoFA());
        editedUser.setAccountBlocked(user.isAccountBlocked());
        editedUser.setAccountBanned(user.isAccountBanned());
        editedUser.setAccountVerified(user.isAccountVerified());
        return this.iUserRepository.save(editedUser);
    }

    public boolean banUser(String username) {
        User user = getUserByUsername(username);
        this.emailService.sendEmail(user.getEmail(), "ChatOverflow Ban", "You have been banned! It is what it is.");
        user.setAccountBanned(true);
        this.saveUser(user);
        return getUserByUsername(username).isAccountBanned();
    }

    public User saveUser(User user) {
        return iUserRepository.save(user);
    }

}
