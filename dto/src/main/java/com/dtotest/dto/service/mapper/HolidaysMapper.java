package com.dtotest.dto.service.mapper;

import com.dtotest.dto.entity.Holidays;
import com.dtotest.dto.entity.Users;
import com.dtotest.dto.service.dto.HolidaysDTO;
import com.dtotest.dto.service.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HolidaysMapper {

    HolidaysDTO entityToDTO (Holidays holidays);

    Holidays DTOToEntity (HolidaysDTO holidaysDTO);

    List<HolidaysDTO> entitiesToDTOs(List<Holidays> holidaysList);

    List<Holidays> DTOsToEntities(List<HolidaysDTO> holidaysDTOList);
}
