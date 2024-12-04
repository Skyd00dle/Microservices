package ru.itmentor.spring.boot_security.demo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.model.Role;

import java.util.List;


@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("FROM Role", Role.class).getResultList();
    }

    @Override
    public Role getRoleById(int id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public void saveRole(Role role) {
        if (role.getId() == 0) {
            entityManager.persist(role);
        } else {
            entityManager.merge(role);
        }
    }

}
