package persistence.until;

import model.Project;
import persistence.entity.ProjectData;

import java.time.Clock;

public class ProjectMapper {


    public static ProjectData toData(Project project) {

        if (project == null) return null;

        ProjectData data = new ProjectData(
                project.getName(),
                project.getStartDate(),
                project.getEndDate(),
                project.getProjectStatus(),
                project.getDescription()
        );


        if (project.getProjectId() != null) {
            data.setId(project.getProjectId());
        }

        return data;
    }

    // Convierte ProjectData (JPA) → Project (dominio)
    public static Project toDomain(ProjectData data) {
        if (data == null) return null;
        return Project.newProject(
                data.getId(),
                data.getName(),
                data.getStartDate(),
                data.getEndDate(),
                data.getProjectStatus(),
                data.getDescription(),
                Clock.systemDefaultZone()
        );
    }
}
