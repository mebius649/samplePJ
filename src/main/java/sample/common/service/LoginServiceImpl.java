package sample.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sample.common.dao.UserDao;
import sample.common.dao.entity.User;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean login(String userId, String password) {

        User user = userDao.findByUserId(userId);

        if (user == null) {
            return false;
        }

        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public boolean signup(String userId, String password) {

        User existingUser = userDao.findByUserId(userId);

        if (existingUser != null) {
            return false;
        }

        String hashedPassword = passwordEncoder.encode(password);

        User user = new User();
        user.setUserId(userId);
        user.setPassword(hashedPassword);

        userDao.insert(user);

        return true;
    }
}