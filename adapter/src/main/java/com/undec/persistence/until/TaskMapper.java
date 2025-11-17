package com.undec.persistence.until;

import model.Task;
import com.undec.persistence.entity.TaskData;

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
