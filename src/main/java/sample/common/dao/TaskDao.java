package sample.common.dao;

import java.util.List;

import sample.common.dao.entity.Task;

public interface TaskDao {

    List<Task> findAll();

    Task findById(Long id);

    void insert(Task task);

    void update(Task task);

    void deleteById(Long id);
}