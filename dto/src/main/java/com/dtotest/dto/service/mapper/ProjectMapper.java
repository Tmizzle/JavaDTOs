package com.dtotest.dto.service.mapper;

import com.dtotest.dto.entity.Project;
import com.dtotest.dto.entity.Users;
import com.dtotest.dto.service.dto.ProjectDTO;
import com.dtotest.dto.service.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectDTO entityToDTO (Project project);

    Project DTOToEntity (ProjectDTO projectDTO);

    List<ProjectDTO> entitiesToDTOs(List<Project> projectList);

    List<Project> DTOsToEntities(List<ProjectDTO> projectDTOList);
}
