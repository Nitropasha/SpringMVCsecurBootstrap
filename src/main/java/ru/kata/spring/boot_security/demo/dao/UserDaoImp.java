package ru.kata.spring.boot_security.demo.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class UserDaoImp implements UserDao {


   @PersistenceContext
   private EntityManager entityManager;
   @Autowired
   private RoleRepository roleRepository;

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = entityManager.createQuery("from User", User.class);
      return query.getResultList();
   }

   @Override
   public void saveUser(User user) {
      if (user.getId() == null) {
         entityManager.persist(user);
      } else {
         entityManager.merge(user);
      }
   }

   @Override
   public User getUser(Long id) {
      User user = entityManager.find(User.class, id);
      return user;
   }

   @Override
   public void deleteUser(Long id) {
      User user = entityManager.find(User.class, id);
      if (user != null) {
         entityManager.remove(user);
      }

   }

   @Override
   public User roleNull(User user) {
      Role userRole = roleRepository.findById(1L)
              .orElseThrow(() -> new RuntimeException("Role USER not found"));
      Set<Role> roles = new HashSet<>();
      roles.add(userRole);
      user.setRoles(roles);
      return user;
   }


}
