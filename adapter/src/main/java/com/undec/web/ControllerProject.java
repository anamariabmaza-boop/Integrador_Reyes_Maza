package com.undec.web;

import com.undec.controller.dto.*;
import input.*;
import model.StatusTask;
import model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
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
    public ResponseEntity<?> createProject(@RequestBody ProjectRequest request) {
        try {
            var project = createProjectInput.createProject(request.toDomainProject(clock));
            return ResponseEntity.ok(ProjectResponse.fromDomainProject(project));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //create task
    @PostMapping("/{projectId}/task")
    public ResponseEntity<?> createTask(@RequestBody TaskRequest request,@PathVariable Long projectId) {
        try {
            var requestTask = request.toDomainTask();
            var task = createTaskInput.createTask(requestTask.getIdTask(),
                    projectId,
                    requestTask.getEstimateHours()
                    , requestTask.getAssignee()
                    , requestTask.getStatus(),
                    requestTask.getFinishedAt(),
                    requestTask.getCreatedAt(), requestTask.getTitle());
            return ResponseEntity.ok(TaskResponse.fromDomainTask(task));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    //get project summary
    @GetMapping("/{projectId}/summary")
    public ResponseEntity<?> getProjectSummary(@PathVariable Long projectId){
        try {
            var summary = getProjectSummaryInput.getProjectSummary(projectId);
            var projectSummary = ProjectSummaryResponse.fromDomainProjectSummary(summary);
            return ResponseEntity.ok(projectSummary);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //find task by project and status
    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<?> findTaskByStatus (@PathVariable Long projectId, @RequestParam StatusTask status) {
        try {
            List<Task> tasks = findTaskByProjectAndStatusInput.findTasksByProjectAndStatus(projectId, status);
            List<TaskResponse> response = tasks.stream().map(TaskResponse::fromDomainTask).toList();
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    //exportProjectTask
    //Aca se podria transformar en CSV? el resultado
    @GetMapping("/{projectId}/tasks/export")
    public ResponseEntity<?> exportTasks(@PathVariable Long projectId) {
        try {
            List<String> exported = exportProjectTaskInput.exportProjectTask(projectId);
            return ResponseEntity.ok(exported);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
