package sample.common.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sample.common.dao.entity.User;
import sample.common.dao.mapper.UserMapper;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserId(String userId) {
        return userMapper.findByUserId(userId);
    }



    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }
}