package persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import persistence.entity.ProjectData;

public interface ProjectRepositoryImpl extends JpaRepository<ProjectData, Long> {
}
