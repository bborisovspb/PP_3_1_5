package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.util.HashSet;
import java.util.List;

@Component
public class InitCommandLinerImpl implements CommandLineRunner {


    private final UserService userService;

    @Autowired
    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public InitCommandLinerImpl(UserServiceImpl userService) {
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {


        User user1 = new User("Ivan", "Petrov", 35, "ivan@mail.com", "ivan@mail.com",
                "ivan", new HashSet<>(List.of(Role.ROLE_USER)));

        User user2 = new User("Oleg", "Ivanov", 36, "oleg@mail.com", "oleg@mail.com",
                "oleg", new HashSet<>(List.of(Role.ROLE_USER, Role.ROLE_ADMIN)));


        User user3 = new User("Pavel", "Borisov", 37, "pavel@mail.com", "pavel@mail.com",
                "pavel", new HashSet<>(List.of(Role.ROLE_ADMIN)));

        User user4 = new User("Boris", "Pavlov", 38, "boris@mail.com", "boris@mail.com",
                "boris", new HashSet<>(List.of(Role.ROLE_USER, Role.ROLE_ADMIN)));

        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);
        userService.saveUser(user4);
    }


}
