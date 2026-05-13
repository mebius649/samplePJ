package sample.common.service;

import java.util.List;
import sample.common.dao.entity.Task;

public interface TaskService {
    
    List<Task> findAllByUsername(String username);

    void save(Task task);
    
    Task findByIdAndUsername(Long id, String username);
    
    void update(Task task);

    void delete(Long id, String username);
}