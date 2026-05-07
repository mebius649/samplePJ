package sample.common.dao;

import sample.common.dao.entity.User;

public interface UserDao {

    User findByUserId(String userId);

    User findByUserIdAndPassword(String userId, String password);

    void insert(User user);
}