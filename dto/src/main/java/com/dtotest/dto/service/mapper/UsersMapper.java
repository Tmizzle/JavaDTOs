package com.dtotest.dto.service.mapper;

import com.dtotest.dto.entity.Users;
import com.dtotest.dto.service.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    UserDTO entityToDTO (Users user);

    Users DTOToEntity (UserDTO userDTO);

    List<UserDTO> entitiesToDTOs(List<Users> usersList);

    List<Users> DTOsToEntities(List<UserDTO> userDTOList);
}
