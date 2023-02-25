package com.dtotest.dto.service.interfaces;

import com.dtotest.dto.dao.ProjectRepo;
import com.dtotest.dto.entity.Project;
import com.dtotest.dto.service.dto.ProjectDTO;
import com.dtotest.dto.service.mapper.ProjectMapper;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ProjectService {

    private final ProjectRepo projectRepo;
    private final ProjectMapper projectMapper;

    // Retrieves all projects
    public List<ProjectDTO> getProjects() {
        List<Project> projectList = projectRepo.findAll();
        List<ProjectDTO> projectDTOList = projectMapper.entitiesToDTOs(projectList);
        return projectDTOList;
    }
}
