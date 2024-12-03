package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(int id);
    Set<Role> getRolesByIds(List<Integer> ids);
    void saveRole(Role role);
}
