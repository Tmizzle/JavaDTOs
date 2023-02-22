package com.dtotest.dto.service.interfaces;

import com.dtotest.dto.dao.HolidaysRepo;
import com.dtotest.dto.entity.AccountSettings;
import com.dtotest.dto.entity.Holidays;
import com.dtotest.dto.service.dto.AccountSettingsDTO;
import com.dtotest.dto.service.dto.HolidaysDTO;
import com.dtotest.dto.service.mapper.HolidaysMapper;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class HolidaysService {

    private final HolidaysRepo holidaysRepo;
    private final HolidaysMapper holidaysMapper;

    public List<HolidaysDTO> getHolidays() {
        List<Holidays> holidays = holidaysRepo.findAll();
        List<HolidaysDTO> holidaysDTOs = holidaysMapper.entitiesToDTOs(holidays);
        return holidaysDTOs;
    }

    public HolidaysDTO getHolidaysById(Integer id){
        Holidays holidays = holidaysRepo.findById(id).orElseThrow(() -> new IllegalStateException("" +
                "holiday with id " + id + " does not exist"));
        return holidaysMapper.entityToDTO(holidays);
    }
}
