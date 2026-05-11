package sample.common.dao;

import sample.common.dao.entity.User;

public interface UserDao {

    User findByUserId(String userId);


    void insert(User user);
}