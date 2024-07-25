package com.task.service.service;

import com.task.service.model.Task;
import com.task.service.model.TaskStatus;
import com.task.service.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImplementation implements TaskService{
    @Autowired
    private TaskRepository taskrepository;

    @Override
    public Task createTask(Task task, String requesterRole) throws Exception {
        if (!requesterRole.equals(("ROLE_ADMIN"))){
            throw new Exception("only admin can create task");
        }
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        return taskrepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) throws Exception {
        return taskrepository.findById(id).orElseThrow(()-> new Exception("task not found id"+id));
    }

    @Override
    public List<Task> getAllTask(TaskStatus status) {
        List<Task> allTask = taskrepository.findAll();
        List<Task> filteredTasks = allTask.stream().filter(task -> status==null || task.getStatus().name().equalsIgnoreCase(status.toString())).collect(Collectors.toList());
        return filteredTasks;
    }

    @Override
    public Task updateTask(Long id, Task updatedTask, Long userId) throws Exception {
        Task existingTask = getTaskById(id);
        if (updatedTask.getTitle()!=null){
            existingTask.setTitle(updatedTask.getTitle());
        }
        if (updatedTask.getDescription()!=null){
            existingTask.setDescription(updatedTask.getDescription());
        }
        if (updatedTask.getImage()!=null){
            existingTask.setImage(updatedTask.getImage());
        }
        if (updatedTask.getStatus()!=null){
            existingTask.setStatus(updatedTask.getStatus());
        }
        if (updatedTask.getDeadline()!=null){
            existingTask.setDeadline(updatedTask.getDeadline());
        }
        return taskrepository.save(existingTask);
    }

    @Override
    public void deleteTask(Long id) throws Exception {
        getTaskById(id);
        taskrepository.deleteById(id);
    }

    @Override
    public Task assignedToUser(Long userId, Long taskId) throws Exception {
        Task task = getTaskById(taskId);
        task.setAssignedUserId(userId);
        task.setStatus(TaskStatus.DONE);
        return taskrepository.save(task);
    }

    @Override
    public List<Task> assignedUserTask(Long userId, TaskStatus status) {
        List<Task> allTask = taskrepository.findByAssignedUserId(userId);
        List<Task> filteredTasks = allTask.stream().filter(task -> status==null || task.getStatus().name().equalsIgnoreCase(status.toString())).collect(Collectors.toList());
        return filteredTasks;
    }

    @Override
    public Task completeTask(Long taskId) throws Exception {
        Task task = getTaskById(taskId);
        task.setStatus(TaskStatus.DONE);
        return taskrepository.save(task);
    }
}
