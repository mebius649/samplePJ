package sample.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sample.common.dao.entity.Task;

@Mapper
public interface TaskMapper {

    List<Task> findAll();

    Task findById(@Param("id") Long id);

    void insert(Task task);

    void update(Task task);

    void deleteById(@Param("id") Long id);
}