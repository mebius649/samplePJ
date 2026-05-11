package sample.common.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sample.common.dao.entity.User;

@Mapper
public interface UserMapper {

    User findByUserId(@Param("userId") String userId);


    void insert(User user);
}