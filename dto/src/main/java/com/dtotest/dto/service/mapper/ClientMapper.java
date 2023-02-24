package com.dtotest.dto.service.mapper;

import com.dtotest.dto.entity.Client;
import com.dtotest.dto.entity.Holidays;
import com.dtotest.dto.service.dto.ClientDTO;
import com.dtotest.dto.service.dto.HolidaysDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO entityToDTO (Client client);

    Client DTOToEntity (ClientDTO clientDTO);

    List<ClientDTO> entitiesToDTOs(List<Client> clientList);

    List<Client> DTOsToEntities(List<ClientDTO> clientDTOList);
}
