package sample.common.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sample.common.dao.entity.Task;
import sample.common.dao.mapper.TaskMapper;

@Repository
public class TaskDaoImpl implements TaskDao {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<Task> findByUsername(String username) {
        return taskMapper.findByUsername(username);
    }

    @Override
    public Task findByIdAndUsername(Long id, String username) {
        return taskMapper.findByIdAndUsername(id, username);
    }

    @Override
    public void insert(Task task) {
        taskMapper.insert(task);
    }

    @Override
    public void update(Task task) {
        taskMapper.update(task);
    }

    @Override
    public void deleteByIdAndUsername(Long id, String username) {
        taskMapper.deleteByIdAndUsername(id, username);
    }
}