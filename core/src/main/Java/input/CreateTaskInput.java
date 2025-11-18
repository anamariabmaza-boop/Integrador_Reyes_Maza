package input;

import model.StatusTask;
import model.Task;

import java.time.LocalDateTime;

public interface CreateTaskInput {
    Task createTask(Long idTask,
                    Long idProject,
                    Integer estimateHours,
                    String assign,
                    StatusTask status,
                    LocalDateTime finishAt,
                    LocalDateTime createAt,
                    String title);
}
