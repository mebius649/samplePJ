package sample.thymeleaf.web;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.servlet.http.HttpSession;
import sample.common.dao.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import sample.common.dao.TaskDao;
import java.util.List;



@Controller
public class TasksController {

    @Autowired
    private TaskDao taskDao;

    @GetMapping("/tasks")
    public String tasks(Model model) {

        List<Task> allContents = taskDao.findAll();
        model.addAttribute("allContents", allContents);

        return "tasks";
    }

    @GetMapping("/tasks/new")
    public String newForm() {
        return "form-new";
    }

    @PostMapping("/tasks")
    public String createTask(
            @RequestParam("title") String title,
            @RequestParam("contents") String contents,
            @RequestParam("name") String name,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            HttpSession session) { 
        
    	
        String loginUsername = (String) session.getAttribute("loginUsername");

        if (loginUsername == null) {
            return "redirect:/login";
        }
        

        Task task = new Task();

        task.setUsername(loginUsername);
        task.setTitle(title);
        task.setContents(contents);     
        task.setName(name);             


        if (!startDate.isEmpty()) {
            task.setStartDate(LocalDate.parse(startDate));
        }

        if (!endDate.isEmpty()) {
            task.setEndDate(LocalDate.parse(endDate));
        }

        LocalDateTime now = LocalDateTime.now();
        task.setCreatedAt(now);
        task.setUpdatedAt(now);

        taskDao.insert(task);

        return "redirect:/tasks";
    }
    
    @GetMapping("/tasks/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model, HttpSession session) {

        String loginUsername = (String) session.getAttribute("loginUsername");

        if (loginUsername == null) {
            return "redirect:/login";
        }

        Task task = taskDao.findById(id);

        if (task == null) {
            return "redirect:/tasks";
        }

        model.addAttribute("task", task);

        return "form-edit";
    }
    
    @PostMapping("/tasks/edit/{id}")
    public String updateTask(
            @PathVariable("id") Long id,
            @RequestParam("title") String title,
            @RequestParam("contents") String contents,
            @RequestParam("name") String name,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            HttpSession session) {

        Task task = taskDao.findById(id);

        if (task == null) {
            return "redirect:/tasks";
        }

        String loginUsername = (String) session.getAttribute("loginUsername");

        if (loginUsername == null) {
            return "redirect:/login";
        }

        task.setUsername(loginUsername);
        task.setTitle(title);
        task.setContents(contents);
        task.setName(name);

        if (startDate != null && !startDate.isEmpty()) {
            task.setStartDate(LocalDate.parse(startDate));
        } else {
            task.setStartDate(null);
        }

        if (endDate != null && !endDate.isEmpty()) {
            task.setEndDate(LocalDate.parse(endDate));
        } else {
            task.setEndDate(null);
        }

        task.setUpdatedAt(LocalDateTime.now());

        taskDao.update(task);

        return "redirect:/tasks";
    }
    
    @PostMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id, HttpSession session) {

        String loginUsername = (String) session.getAttribute("loginUsername");

        if (loginUsername == null) {
            return "redirect:/login";
        }

        Task task = taskDao.findById(id);

        if (task != null) {
            taskDao.deleteById(id);
        }

        return "redirect:/tasks";
    }
    
}