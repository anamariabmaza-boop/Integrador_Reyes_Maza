package com.undec.config;

import input.*;
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
    public CreateProjectInput createProjectUseCase(ProjectRepository projectRepository, Clock clock) {
        return new CreateProjectUseCase(projectRepository, clock);
    }

   @Bean
   public CreateTaskInput createTaskUseCase(Clock clock, ProjectRepository projectRepository , TaskRepository taskRepository) {
       return new CreateTaskUseCase(clock,projectRepository,taskRepository);
   }
   @Bean
    public ExportProjectTaskInput exportProjectTaskUseCase(ProjectRepository projectRepository, TaskRepository taskRepository) {
        return new ExportProjectTaskUseCase(projectRepository,taskRepository);
   }
   @Bean
    public FindTaskByProjectAndStatusInput findTasksByProjectAndStatusUseCase(ProjectRepository projectRepository, TaskRepository taskRepository) {
        return new FindTasksByProjectAndStatusUseCase(projectRepository,taskRepository);
   }
   @Bean
    public GetProjectSummaryInput getProjectSummaryUseCase(ProjectRepository projectRepository, TaskRepository taskRepository){
        return new GetProjectSummaryUseCase(projectRepository,taskRepository);
   }


}
