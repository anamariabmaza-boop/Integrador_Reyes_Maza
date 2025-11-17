package persistence.until;

import model.Project;
import model.StatusTask;
import model.Task;
import persistence.entity.TaskData;

import java.time.LocalDateTime;

public class TaskMapper {

    public static TaskData mapToTaskData(Task task){

        TaskData  taskData = new TaskData( ProjectMapper.toData(task.getProject()),
                    task.getEstimateHours(),
                    task.getAssignee(),
                    task.getStatus(),
                    task.getFinishedAt(),
                    task.getCreatedAt());

        if (task.getIdTask() != null) {
            taskData.setIdTask(task.getIdTask());
        }

            return taskData;

    }

    public static Task mapToTaskDomain(TaskData task){

        return Task.newTask(task.getIdTask(),
                        ProjectMapper.toDomain(task.getProject()),
                        task.getEstimateHours(),
                        task.getAssignee(),
                        task.getStatus(),
                        task.getFinishedAt(),
                        task.getCreatedAt());

    }


}
