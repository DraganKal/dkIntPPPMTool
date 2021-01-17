package io.dkintelligence.ppmtool.services;

import io.dkintelligence.ppmtool.domain.Backlog;
import io.dkintelligence.ppmtool.domain.Project;
import io.dkintelligence.ppmtool.domain.ProjectTask;
import io.dkintelligence.ppmtool.exceptions.ProjectNotFoundException;
import io.dkintelligence.ppmtool.repositories.BacklogRepository;
import io.dkintelligence.ppmtool.repositories.ProjectRepository;
import io.dkintelligence.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private ProjectTaskRepository projectTaskRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
        try {
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
            projectTask.setBacklog(backlog);
            Integer backlogSequence = backlog.getPTSequence();
            backlogSequence++;
            backlog.setPTSequence(backlogSequence);
            projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);
            if (projectTask.getPriority() == null) {
                projectTask.setPriority(3);
            }
            if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
                projectTask.setStatus("TO_DO");
            }
            return projectTaskRepository.save(projectTask);
        }catch (Exception e){
            throw new ProjectNotFoundException("Project with id '" + projectIdentifier + "' not found");
        }

    }

    public Iterable<ProjectTask> findBacklogById(String backlog_id) {

        Project project = projectRepository.findByProjectIdentifier(backlog_id);
        if(project == null){
            throw new ProjectNotFoundException("Project with id '" + backlog_id + "' does not exist");
        }

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
    }
}
