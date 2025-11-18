package com.undec.config;

import org.hibernate.boot.model.relational.QualifiedName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import output.ProjectRepository;
import output.TaskRepository;
import usecase.*;

import java.time.Clock;

@Configuration
public class GlobalConfig {
    @Bean
    public Clock systemClock(){
        return  Clock.systemDefaultZone();
    }

    @Bean
    public CreateProjectUseCase  createProjectUseCase(ProjectRepository projectRepository,Clock clock) {
        return new CreateProjectUseCase(projectRepository, clock);
    }

   @Bean
   public CreateTaskUseCase createTaskUseCase(Clock clock,ProjectRepository projectRepository ,TaskRepository taskRepository) {
       return new CreateTaskUseCase(clock,projectRepository,taskRepository);
   }
   @Bean
    public ExportProjectTaskUseCase exportProjectTaskUseCase(ProjectRepository projectRepository,TaskRepository taskRepository) {
        return new ExportProjectTaskUseCase(projectRepository,taskRepository);
   }
   @Bean
    public FindTasksByProjectAndStatusUseCase findTasksByProjectAndStatusUseCase(ProjectRepository projectRepository,TaskRepository taskRepository) {
        return new FindTasksByProjectAndStatusUseCase(projectRepository,taskRepository);
   }
   @Bean
    public GetProjectSummaryUseCase getProjectSummaryUseCase(ProjectRepository projectRepository,TaskRepository taskRepository){
        return new GetProjectSummaryUseCase(projectRepository,taskRepository);
   }


}
