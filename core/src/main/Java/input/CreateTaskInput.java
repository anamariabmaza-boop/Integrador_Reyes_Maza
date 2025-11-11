package input;

import model.Task;
import model.StatusTask;

import java.time.LocalDateTime;

public interface CreateTaskInput {
    Task createTask(Long idTask,
                    Long idProject,
                    Integer estimateHours,
                    String assign,
                    StatusTask status,
                    LocalDateTime finishAt,
                    LocalDateTime createAt);
}
