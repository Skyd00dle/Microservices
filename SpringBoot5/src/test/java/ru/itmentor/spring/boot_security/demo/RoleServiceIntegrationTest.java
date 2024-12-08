package ru.itmentor.spring.boot_security.demo;


import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.service.RoleService;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class RoleServiceIntegrationTest {

    @Autowired
    private RoleService roleService;

    private Role testRole;

    @BeforeEach
    public void setUp() {
        testRole = new Role();
        testRole.setName("ROLE_USER");
        roleService.saveRole(testRole);
    }

    @Test
    public void testGetAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        assertNotNull(roles);
        assertTrue(roles.size() > 0);
    }

    @Test
    public void testGetRoleById() {
        Role role = roleService.getRoleById(testRole.getId());
        assertNotNull(role);
        assertEquals("ROLE_USER", role.getName());
    }

    @Test
    public void testSaveRole() {
        Role newRole = new Role();
        newRole.setName("ROLE_ADMIN");
        roleService.saveRole(newRole);
        Role retrievedRole = roleService.getRoleById(newRole.getId());
        assertNotNull(retrievedRole);
        assertEquals("ROLE_ADMIN", retrievedRole.getName());
    }

    @Test
    public void testGetRolesByIds() {
        Role anotherRole = new Role();
        anotherRole.setName("ROLE_HR");
        roleService.saveRole(anotherRole);
        Set<Role> roles = roleService.getRolesByIds(List.of(testRole.getId(), anotherRole.getId()));
        assertEquals(2,roles.size());
    }

}
