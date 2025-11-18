package com.undec.persistence.repository;

import output.ProjectRepository;
import com.undec.persistence.crud.ProjectRepositoryCrud;
import com.undec.persistence.entity.ProjectData;
import com.undec.persistence.until.ProjectMapper;
import model.Project;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

    private final ProjectRepositoryCrud crud;

    public ProjectRepositoryImpl(ProjectRepositoryCrud crud) {
        this.crud = crud;
    }

    @Override
    public boolean existsByName(String name) {
        return crud.findByName(name).iterator().hasNext();
    }

    @Override
    public Project save(Project validProject) {
        ProjectData data = ProjectMapper.toData(validProject);
        ProjectData saved = crud.save(data);
        return ProjectMapper.toDomain(saved);
    }

    @Override
    public Project findProjectById(Long idProject) {
        return crud.findById(idProject)
                .map(ProjectMapper::toDomain)
                .orElse(null);
    }
}
