package ru.itmentor.spring.boot_security.demo.dao;

import ru.itmentor.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    List<Role> getAllRoles();
    Role getRoleById(int id);
    void saveRole(Role role);
    Set<Role> findByIdRoles(List<Integer>roles);
}
