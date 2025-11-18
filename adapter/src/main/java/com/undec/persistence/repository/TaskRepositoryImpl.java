package com.undec.persistence.repository;

import model.Task;
import org.springframework.stereotype.Repository;
import output.TaskRepository;
import com.undec.persistence.crud.TaskRepositoryCrud;
import com.undec.persistence.entity.TaskData;
import com.undec.persistence.until.TaskMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Esta interfaz ES mi repositorio.
 * No tiene código adentro.
 * No implementa metodos.
 * Spring Data JPA automáticamente crea la implementación en tiempo de ejecución.
 * Por que funciona?
 * Porque extends JpaRepository le dice a Spring:
 *  "Generame el CRUD completo para ProjectData con ID tipo Long".
 */
@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final TaskRepositoryCrud  taskRepositoryCrud;

    public TaskRepositoryImpl(TaskRepositoryCrud taskRepositoryCrud) {
        this.taskRepositoryCrud = taskRepositoryCrud;
    }

    @Override
    public List<Task> findByProject(Long projectId) {

        return taskRepositoryCrud.findByProject_Id(projectId).
                                    stream().
                                    map(TaskMapper::mapToTaskDomain).
                                    collect(Collectors.toList());
    }
    @Override
    public Task saveTask(Task task) {
        TaskData data = TaskMapper.mapToTaskData(task);
        TaskData saved = taskRepositoryCrud.save(data);
        return TaskMapper.mapToTaskDomain(saved);

    }
}
