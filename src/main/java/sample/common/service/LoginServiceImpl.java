package sample.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.common.dao.entity.User;
import sample.common.dao.UserDao;

import sample.common.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean login(String userId, String password) {
        User user = userDao.findByUserIdAndPassword(userId, password);
        return user != null;
    }

    @Override
    public boolean signup(String userId, String password) {
        User existingUser = userDao.findByUserId(userId);

        if (existingUser != null) {
            return false;
        }

        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);

        userDao.insert(user);

        return true;
    }
}