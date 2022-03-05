package ro.bogdan.chatoverflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.bogdan.chatoverflow.model.Role;
import ro.bogdan.chatoverflow.repository.IRoleRepository;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private IRoleRepository iRoleRepository;

    public List<Role> findRoles(){
        return (List<Role>) iRoleRepository.findAll();
    }

}