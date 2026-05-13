package sample.common.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.common.dao.TaskDao;
import sample.common.dao.entity.Task;
import sample.common.service.TaskService;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Override
    public List<Task> findAllByUsername(String username) {
        return taskDao.findByUsername(username);
    }

    @Override
    public void save(Task task) {
        
        LocalDateTime now = LocalDateTime.now();
        task.setCreatedAt(now);
        task.setUpdatedAt(now);
        taskDao.insert(task);
    }

    @Override
    public Task findByIdAndUsername(Long id, String username) {
        return taskDao.findByIdAndUsername(id, username);
    }

    @Override
    public void update(Task task) {
        task.setUpdatedAt(LocalDateTime.now());
        taskDao.update(task);
    }

    @Override
    public void delete(Long id, String username) {
        taskDao.deleteByIdAndUsername(id, username);
    }
}