package ro.bogdan.chatoverflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.bogdan.chatoverflow.model.Role;
import ro.bogdan.chatoverflow.service.RoleService;

import java.util.List;

@Controller
@RequestMapping(value = "/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    @ResponseBody
    private List<Role> getRoles() {
        return roleService.getRoles();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ResponseBody
    private Role addRole(@RequestParam(name = "name") String name) {
        return roleService.addRole(name);
    }
}
