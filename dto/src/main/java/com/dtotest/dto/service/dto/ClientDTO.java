package com.dtotest.dto.service.dto;

import lombok.Data;

@Data
public class ClientDTO {
    private String name;
    private String internalCode;
    private String industry;
    private String country;
    private String city;
    private String streetName;
    private String streetNumber;
    private String zip;
}
