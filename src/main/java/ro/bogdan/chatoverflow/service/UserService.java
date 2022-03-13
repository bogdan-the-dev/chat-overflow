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

    public List<User> getUserByUsername(String username){
        return (List<User>) iUserRepository.findUserByUsernameContainingIgnoreCase(username);
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

    public User saveUser(User user) {
        return iUserRepository.save(user);
    }

}
