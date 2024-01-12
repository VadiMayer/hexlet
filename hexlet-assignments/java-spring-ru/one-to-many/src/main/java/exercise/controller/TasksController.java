package exercise.controller;

import java.util.List;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
import exercise.model.Task;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;
import jakarta.validation.Valid;

/*
Реализуйте полный CRUD сущности задач. Создайте обработчики для просмотра списка всех задач и конкретной задачи,
создания, обновления и удаления задачи:

GET /tasks – просмотр списка всех задач
GET /tasks/{id} – просмотр конкретной задачи
POST /tasks – создание новой задачи
PUT /tasks/{id} – редактирование задачи. При редактировании мы должны иметь возможность поменять название,
описание задачи и ответственного разработчика
DELETE /tasks/{id} – удаление задачи
 */


@RestController
@RequestMapping("/tasks")
public class TasksController {
    // BEGIN
    private TaskRepository taskRepository;

    private TaskMapper taskMapper;

    @GetMapping
    public List<TaskDTO> getTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> taskMapper.map(task))
                .toList();
    }

    @GetMapping("/{id}")
    public TaskDTO getTask(@PathVariable long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
        return taskMapper.map(task);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@RequestBody TaskCreateDTO createDTO) {
        Task task = taskMapper.map(createDTO);
        taskRepository.save(task);
        return taskMapper.map(task);
    }
    // END
}

/*
POST /tasks – создание новой задачи
PUT /tasks/{id} – редактирование задачи. При редактировании мы должны иметь возможность поменять название,
описание задачи и ответственного разработчика
DELETE /tasks/{id} – удаление задачи
 */