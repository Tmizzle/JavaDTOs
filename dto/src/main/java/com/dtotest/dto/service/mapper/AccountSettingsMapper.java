package com.dtotest.dto.service.mapper;

import com.dtotest.dto.entity.AccountSettings;
import com.dtotest.dto.service.dto.AccountSettingsDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountSettingsMapper{

    AccountSettingsDTO entityToDTO (AccountSettings accountSettings);

    AccountSettings DTOToEntity (AccountSettingsDTO accountSettingsDTO);

    List<AccountSettingsDTO> entitiesToDTOs(List<AccountSettings> accountSettingsList);

    List<AccountSettings> DTOsToEntities(List<AccountSettingsDTO> accountSettingsDTOList);
}
