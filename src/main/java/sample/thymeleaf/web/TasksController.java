package sample.thymeleaf.web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sample.common.dao.entity.Task;
import sample.common.form.TaskForm;
import sample.common.service.TaskService;

@Controller
public class TasksController {

	@Autowired
	private TaskService taskService;

    
    @GetMapping("/tasks")
    public String tasks(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String loginUsername = userDetails.getUsername(); 

        List<Task> allContents = taskService.findAllByUsername(loginUsername);
        model.addAttribute("allContents", allContents);

        return "tasks";
    }

    @GetMapping("/tasks/new")
    public String newForm() {
        return "form-new";
    }

    @PostMapping("/tasks")
    public String createTask(
            TaskForm form, 
            @AuthenticationPrincipal UserDetails userDetails) {
        
        String loginUsername = userDetails.getUsername();
          

        Task task = new Task();
        task.setUsername(loginUsername);
        task.setTitle(form.getTitle());
        task.setContents(form.getContents());
        task.setName(form.getName());

        
        if (form.getStartDate() != null && !form.getStartDate().isEmpty()) {
            task.setStartDate(LocalDate.parse(form.getStartDate()));
        }

        if (form.getEndDate() != null && !form.getEndDate().isEmpty()) {
            task.setEndDate(LocalDate.parse(form.getEndDate()));
        }

        taskService.save(task);

        return "redirect:/tasks";
    }
    
    
    
    @GetMapping("/tasks/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
    	String loginUsername = userDetails.getUsername();

        Task task = taskService.findByIdAndUsername(id, loginUsername);
        if (task == null) {
            return "redirect:/tasks";
        }

        model.addAttribute("task", task);
        return "form-edit";
    }
    
    
    @PostMapping("/tasks/edit/{id}")
    public String updateTask(
            @PathVariable("id") Long id,
            TaskForm form,
            @AuthenticationPrincipal UserDetails userDetails) {

        String loginUsername = userDetails.getUsername();

        Task task = taskService.findByIdAndUsername(id, loginUsername);
        if (task == null) {
            return "redirect:/tasks";
        }

        task.setTitle(form.getTitle());
        task.setContents(form.getContents());
        task.setName(form.getName());

        task.setStartDate(form.getStartDate() != null && !form.getStartDate().isEmpty() ? LocalDate.parse(form.getStartDate()) : null);
        task.setEndDate(form.getEndDate() != null && !form.getEndDate().isEmpty() ? LocalDate.parse(form.getEndDate()) : null);
        task.setUpdatedAt(LocalDateTime.now());
        
        taskService.update(task);
        
        return "redirect:/tasks";
    }
    
    
    @PostMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        String loginUsername = userDetails.getUsername();

        Task task = taskService.findByIdAndUsername(id, loginUsername);
        if (task != null) {
            taskService.delete(id, loginUsername);
        }

        return "redirect:/tasks";
    }
}