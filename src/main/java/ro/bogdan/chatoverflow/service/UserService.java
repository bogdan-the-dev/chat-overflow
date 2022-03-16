package ro.bogdan.chatoverflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.bogdan.chatoverflow.model.User;
import ro.bogdan.chatoverflow.repository.IUserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private IUserRepository iUserRepository;

    public List<User> getUsers(){
        return (List<User>) iUserRepository.findAll() ;
    }

    public User getUserByUsername(String username){
        return iUserRepository.findUserByUsernameIs(username).orElse(null);
    }

    public User getUserById(Integer id) {
        return iUserRepository.findById(id).orElse(null);
    }

    public void deleteUser(Integer id) throws Exception {
        try {
            iUserRepository.delete(this.getUserById(id));
        } catch (Exception e){
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

    public User saveUser(User user) {
        return iUserRepository.save(user);
    }

}
