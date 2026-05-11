package sample.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sample.common.dao.entity.Task;

@Mapper
public interface TaskMapper {

    List<Task> findByUsername(@Param("username") String username);

    Task findByIdAndUsername(
            @Param("id") Long id,
            @Param("username") String username
    );

    void insert(Task task);

    void update(Task task);

    void deleteByIdAndUsername(
            @Param("id") Long id,
            @Param("username") String username
    );
}