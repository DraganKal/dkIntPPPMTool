package io.dkintelligence.ppmtool.exceptions;

public class ProjectNotFoundExceptionResponse {

    private String ProjectNotFound;

    public ProjectNotFoundExceptionResponse(String ProjectNotFound){
        this.ProjectNotFound = ProjectNotFound;
    }

    public String getProjectNotFound() {
        return ProjectNotFound;
    }
    public void setProjectNotFound(String ProjectNotFound) {
        this.ProjectNotFound = ProjectNotFound;
    }
}
