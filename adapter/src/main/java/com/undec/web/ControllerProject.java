package com.undec.web;

import com.undec.controller.dto.ProjectRequest;
import com.undec.controller.dto.ProjectResponse;
import com.undec.controller.dto.TaskRequest;
import com.undec.controller.dto.TaskResponse;
import input.*;
import model.StatusTask;
import model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usecase.ProjectSummaryResponseModel;


import java.time.Clock;
import java.util.List;

@RestController
@RequestMapping("/project")    //(?
public class ControllerProject {
    private final CreateProjectInput createProjectInput;
    private final CreateTaskInput createTaskInput;
    private final GetProjectSummaryInput getProjectSummaryInput;
    private final FindTaskByProjectAndStatusInput findTaskByProjectAndStatusInput;
    private final ExportProjectTaskInput exportProjectTaskInput;
    private final Clock clock;

    public ControllerProject(CreateProjectInput createProjectInput, CreateTaskInput createTaskInput, GetProjectSummaryInput getProjectSummaryInput, FindTaskByProjectAndStatusInput findTaskByProjectAndStatusInput, ExportProjectTaskInput exportProjectTaskInput, Clock clock) {
        this.createProjectInput = createProjectInput;
        this.createTaskInput = createTaskInput;
        this.getProjectSummaryInput = getProjectSummaryInput;
        this.findTaskByProjectAndStatusInput = findTaskByProjectAndStatusInput;
        this.exportProjectTaskInput = exportProjectTaskInput;
        this.clock = clock;
    }
    // create porject
    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest request) {
        var project = createProjectInput.createProject(request.toDomainProject(clock));
        return ResponseEntity.ok(ProjectResponse.fromDomainProject(project));
    }

    //create task
    /*@PostMapping
    public ResponseEntity<ProjectResponse> createTask(@RequestBody TaskRequest request) {
        var task = createTaskInput.createTask(request.toDomainTask());
            return  ResponseEntity.ok(TaskResponse.fromDomainTask(task));
        }
    }*/

    //get project summary
    @GetMapping("/{projectId}/summary")
    public ResponseEntity<ProjectSummaryResponseModel> getProjectSummary(@PathVariable Long projectId){
    var summary = getProjectSummaryInput.getProjectSummary(projectId);

    return ResponseEntity.ok(summary);
    }

    //find task by project and status
    /*@GetMapping("/{projectId}/tasks/export")
    public ResponseEntity<List<Task>> findTaskByStatus (@PathVariable Long projectId, @RequestParam StatusTask status) {
        List<Task> tasks = findTaskByProjectAndStatusInput.findTasksByProjectAndStatus(projectId, status);
        List<Task> response = tasks.stream().map(TaskResponse::fromDomainTasks).toList();
        return ResponseEntity.ok(response);
    }*/


    //exportProjectTask
    //Aca se podria transformar en CSV? el resultado
    @GetMapping("/{projectId}/tasks/export")
    public ResponseEntity<List<String>> exportTasks(@PathVariable Long projectId) {
        List<String> exported = exportProjectTaskInput.exportProjectTask(projectId);
        return ResponseEntity.ok(exported);
    }

}
