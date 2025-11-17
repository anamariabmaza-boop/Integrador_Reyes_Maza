package persistence.repository;

import model.Project;
import model.Task;
import org.springframework.stereotype.Repository;
import output.TaskRepository;
import persistence.crud.TaskRepositoryCrud;
import persistence.entity.TaskData;
import org.springframework.data.jpa.repository.JpaRepository;
import persistence.until.TaskMapper;

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

        return taskRepositoryCrud.findByProjectwithID(projectId).
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
