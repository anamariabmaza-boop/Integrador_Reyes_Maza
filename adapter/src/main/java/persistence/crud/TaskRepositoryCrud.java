package persistence.crud;

import org.springframework.data.repository.CrudRepository;
import persistence.entity.ProjectData;
import persistence.entity.TaskData;

import java.util.List;

public interface TaskRepositoryCrud extends CrudRepository<TaskData,Long> {
    List<TaskData> findByProjectwithID(long projectId);


}
