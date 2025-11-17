package persistence.until;

import model.Project;
import persistence.entity.ProjectData;

import java.time.Clock;

public class ProjectMapper {

    // Convierte Project (dominio) → ProjectData (JPA)
    public static ProjectData toData(Project project) {
        if (project == null) return null;

        ProjectData data = new ProjectData(
                project.getName(),
                project.getStartDate(),
                project.getEndDate(),
                project.getProjectStatus(),
                project.getDescription()
        );
       // IMPORTANTE: mantener el ID si existe
        if (project.getProjectId() != null) {
            data.setId(project.getProjectId());
        }
/* Esta validacion vendria a ser para que no cree un nuevo proyecto cada vez
       y que conserve el ID si ya existe
       if (project.getProjectId() != null) {
            ProjectData.setId(project.getProjectId());
        }
        Debo poner o no un setId??
        */
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
