package com.undec.config;

import org.hibernate.boot.model.relational.QualifiedName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import output.ProjectRepository;
import output.TaskRepository;
import usecase.CreateProjectUseCase;
import usecase.CreateTaskUseCase;

import java.time.Clock;

@Configuration
public class GlobalConfig {

 //   @Bean
 //   public CreateProjectUseCase  createProjectUseCase(ProjectRepository projectRepository) {
 //       return new CreateProjectUseCase(projectRepository);
 //   }

    @Bean
    public Clock systemClock(){
        return  Clock.systemDefaultZone();
    }
   @Bean
   public CreateTaskUseCase createTaskUseCase(Clock clock,ProjectRepository projectRepository ,TaskRepository taskRepository) {
       return new CreateTaskUseCase(clock,projectRepository,taskRepository);
   }

}
