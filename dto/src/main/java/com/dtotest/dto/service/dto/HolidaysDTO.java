package com.dtotest.dto.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class HolidaysDTO {

    private CountryDTO country;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String name;
}
