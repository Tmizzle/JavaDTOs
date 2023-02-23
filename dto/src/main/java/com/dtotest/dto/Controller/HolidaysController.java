package com.dtotest.dto.Controller;

import com.dtotest.dto.entity.Holidays;
import com.dtotest.dto.entity.Users;
import com.dtotest.dto.service.dto.HolidaysDTO;
import com.dtotest.dto.service.interfaces.HolidaysService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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

    @PutMapping(path = "{id}")
    public void updateAccountSettings(@PathVariable("id") Integer Id,
                                      @RequestParam(required = false) String date,
                                      @RequestParam(required = false) String name){
        if(date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthDate = LocalDate.parse(date, formatter);
            LocalDateTime birthDateTime = birthDate.atStartOfDay();
            Date dateFinal = Date.from(birthDateTime.atZone(ZoneId.systemDefault()).toInstant());
            holidaysService.updateHolidays(Id, dateFinal, name);
        } else {
            holidaysService.updateHolidays(Id, null, name);
        }
    }
    @PostMapping
    public void addNewUser(@RequestBody Holidays holidays, @RequestParam String country){
        holidaysService.addNewHoliday(holidays, country);
    }
}
