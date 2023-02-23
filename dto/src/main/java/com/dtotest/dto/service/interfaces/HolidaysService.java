package com.dtotest.dto.service.interfaces;

import com.dtotest.dto.dao.CountryRepo;
import com.dtotest.dto.dao.HolidaysRepo;
import com.dtotest.dto.entity.AccountSettings;
import com.dtotest.dto.entity.Country;
import com.dtotest.dto.entity.Holidays;
import com.dtotest.dto.service.dto.HolidaysDTO;
import com.dtotest.dto.service.mapper.HolidaysMapper;
import lombok.Data;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Data
@Setter
public class HolidaysService {

    private final HolidaysRepo holidaysRepo;
    private final HolidaysMapper holidaysMapper;
    private final CountryRepo countryRepo;

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

    public List<HolidaysDTO> getHolidaysByCountry(String country){
        List<Holidays> holidays = holidaysRepo.findHolidayByCountry(country);
        List<HolidaysDTO> holidaysDTOs = holidaysMapper.entitiesToDTOs(holidays);
        return holidaysDTOs;
    }

    @Transactional
    public void updateHolidays(Integer Id, Date date, String name) {
        Holidays holidays = holidaysRepo.findById(Id).orElseThrow(() -> new IllegalStateException("" +
                "holiday with id " + Id + " does not exist"));

        if (date != null && !Objects.equals(holidays.getDate(), date)) {
            holidays.setDate(date);
        }

        if (name != null && name.length() > 0 && !Objects.equals(holidays.getName(), name)) {
            holidays.setName(name);
        }
    }

    public void addNewHoliday(Holidays holidays, String country){
        Integer countryId = countryRepo.findCountry(country);
        Country defaultCountry = countryRepo.findById(countryId)
                .orElseThrow(() -> new EntityNotFoundException("Country not found"));
        holidays.setCountry(defaultCountry);
        holidaysRepo.save(holidays);
    }
}
