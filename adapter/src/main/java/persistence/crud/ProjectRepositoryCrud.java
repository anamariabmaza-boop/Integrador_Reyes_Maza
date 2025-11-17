package persistence.crud;

import model.StatusProject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import persistence.entity.ProjectData;

@Repository
public interface ProjectRepositoryCrud extends CrudRepository<ProjectData, Long> {
    Iterable<ProjectData> findByName(String name);
    Iterable<ProjectData> findByProjectStatus(StatusProject projectStatus);
    boolean existsByName(String name);

}
