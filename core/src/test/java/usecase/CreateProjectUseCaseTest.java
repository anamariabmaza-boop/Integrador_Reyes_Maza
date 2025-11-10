package usecase;

import exception.DuplicateResourceException;
import model.Project;
import model.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import output.ProjectRepository;

import java.time.Clock;
import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateProjectUseCaseTest {

    @Mock
    private ProjectRepository projectRepository;

    private final Clock clock = Clock.systemDefaultZone();

    @Test
    void createProject_NameAlreadyExists_ThrowsDuplicateResourceException() {
        Project project = Project.newProject(
                null,
                "Website Redesign",
                LocalDate.now(clock),
                LocalDate.now(clock).plusDays(5),
                Status.PLANNED,
                "Migrate and redesign website",
                clock
        );

        when(projectRepository.existsByName(project.getName())).thenReturn(true);

        CreateProjectUseCase useCase = new CreateProjectUseCase(projectRepository, clock);

        Assertions.assertThrows(DuplicateResourceException.class,
                () -> useCase.createProject(project));
    }

    @Test
    void createProject_ValidProject_SavesAndReturnsProject() {
        Project project = Project.newProject(
                null,
                "Website Redesign",
                LocalDate.now(clock),
                LocalDate.now(clock).plusDays(5),
                Status.PLANNED,
                "Migrate and redesign website",
                clock
        );

        when(projectRepository.existsByName(project.getName())).thenReturn(false);
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        CreateProjectUseCase useCase = new CreateProjectUseCase(projectRepository, clock);

        Project result = useCase.createProject(project);

        verify(projectRepository).save(any(Project.class));
        Assertions.assertEquals(project.getName(), result.getName());
    }
}
