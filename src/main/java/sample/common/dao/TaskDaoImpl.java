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
    public List<Task> findAll() {
        return taskMapper.findAll();
    }

    @Override
    public Task findById(Long id) {
        return taskMapper.findById(id);
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
    public void deleteById(Long id) {
        taskMapper.deleteById(id);
    }
}