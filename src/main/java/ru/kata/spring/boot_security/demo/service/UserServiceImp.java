package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }
    @Transactional
    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }
    @Transactional(readOnly = true)
    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }
    public List<Role> getAllRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User roleNull(User user) {
        return userDao.roleNull(user);
    }

}
