package ro.bogdan.chatoverflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.bogdan.chatoverflow.model.Role;
import ro.bogdan.chatoverflow.service.RoleService;

import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET, value = "/roles")
    @ResponseBody
    private List<Role> getRoles(){
        return roleService.findRoles();
    }

}
