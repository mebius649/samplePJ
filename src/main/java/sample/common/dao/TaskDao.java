package sample.common.dao;

import java.util.List;

import sample.common.dao.entity.Task;

public interface TaskDao {

    List<Task> findByUsername(String username);

    Task findByIdAndUsername(Long id, String username);

    void insert(Task task);

    void update(Task task);

    void deleteByIdAndUsername(Long id, String username);
}