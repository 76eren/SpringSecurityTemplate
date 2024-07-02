package org.example.springsecuritytemplate.Seeder;

import org.example.springsecuritytemplate.Dao.UserDao;
import org.example.springsecuritytemplate.Model.Role;
import org.example.springsecuritytemplate.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@RequiredArgsConstructor
@Component
public class UserSeeder {
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;

    public void seed() {
        for (User user : getUsers()) {
            this.userDao.save(user);
        }
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(createAdmin("admin", "admin"));
        return users;
    }


    public User createAdmin(String username, String password) {
        return User
                .builder()
                .username(username)
                .role(Role.ADMIN)
                .password(passwordEncoder.encode(password))
                .build();
    }
}
