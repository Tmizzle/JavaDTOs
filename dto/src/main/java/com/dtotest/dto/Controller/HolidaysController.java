package com.dtotest.dto.Controller;

import com.dtotest.dto.service.dto.AccountSettingsDTO;
import com.dtotest.dto.service.dto.HolidaysDTO;
import com.dtotest.dto.service.interfaces.AccountSettingsService;
import com.dtotest.dto.service.interfaces.HolidaysService;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.OperatingSystemMXBean;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/holidays")
@Data
public class HolidaysController {

    private final HolidaysService holidaysService;

    @GetMapping
    public List<HolidaysDTO> getHolidays(){
        return holidaysService.getHolidays();
    }

    @GetMapping(path = "{id}")
    public HolidaysDTO getHolidaysById(@PathVariable("id") Integer id){
        return holidaysService.getHolidaysById(id);
    }

    @GetMapping(path = "/find/{country}")
    public List<HolidaysDTO> getHolidaysByCountry(@PathVariable("country") String country){
        return holidaysService.getHolidaysByCountry(country);
    }
}
