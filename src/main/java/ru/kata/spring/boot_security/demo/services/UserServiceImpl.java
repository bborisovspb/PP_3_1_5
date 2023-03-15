package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public List<User> getAllUsers(){
        return usersRepository.findAll();
    }
    public User userInfo(int id){
        return usersRepository.findById(id).orElse(null);
    }

    @Transactional
    public boolean saveUser(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        setRoles(user);
        usersRepository.saveAndFlush(user);
        return true;
    }
    @Transactional
    public void updateUser(User user){
        usersRepository.save(user);
    }

    public void deleteUser(int id) {
        usersRepository.deleteById(id);
    }
    public User findByUserName(String name) {
        return usersRepository.findByUsername(name).get();
    }

    public void setRoles(User user) {

        Set<Role> roles = user.getRoles();
        Set<Role> rolesNew = new HashSet<>();

        for(Role role : roles) {
            if (role.equals(Role.ROLE_ADMIN)) {
                rolesNew.add(Role.ROLE_ADMIN);
            } else if (role.equals(Role.ROLE_USER)) {
                rolesNew.add(Role.ROLE_USER);
            } else {
                System.out.println(role.toString());
                rolesNew.add(Role.ROLE_UNKNOWN);
            }
        }
        user.setRoles(rolesNew);
        roleRepository.saveAll(user.getRoles());

    }


}
