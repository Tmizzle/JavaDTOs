package com.dtotest.dto.Controller;

import com.dtotest.dto.service.dto.ProjectDTO;
import com.dtotest.dto.service.interfaces.ProjectService;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/project")
@Data
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public List<ProjectDTO> getProjects(){
        return projectService.getProjects();
    }
}
