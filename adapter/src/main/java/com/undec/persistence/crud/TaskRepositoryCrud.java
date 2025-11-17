package com.undec.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import com.undec.persistence.entity.TaskData;

import java.util.List;

public interface TaskRepositoryCrud extends CrudRepository<TaskData,Long> {
    List<TaskData> findByProjectwithID(long projectId);

}
