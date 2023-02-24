package com.dtotest.dto.Service;

import com.dtotest.dto.dao.CountryRepo;
import com.dtotest.dto.dao.HolidaysRepo;
import com.dtotest.dto.entity.Country;
import com.dtotest.dto.entity.Holidays;
import com.dtotest.dto.service.interfaces.HolidaysService;
import com.dtotest.dto.service.mapper.HolidaysMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HolidaysServiceTest {

    @Mock
    private HolidaysMapper holidaysMapper;

    @Mock
    private HolidaysRepo holidaysRepo;

    @Mock
    private CountryRepo countryRepo;

    @InjectMocks
    private HolidaysService holidaysService;

    @Test
    public void test(){

        Holidays h = new Holidays();
        h.setId(1);
        h.setName("Praznik");
        h.setDate(new Date(2023,05,02));
        Country country = new Country();
        country.setId(1);
        country.setName("Croatia");
        h.setCountry(country);
        holidaysRepo.save(h);

        System.out.println(holidaysService.getHolidays());

    }
    @Test
    public void testAddNewHoliday() {
        // Arrange
        Holidays holidays = new Holidays();
        holidays.setName("Independence Day");
        holidays.setDate(new Date(2023, Calendar.JUNE,3));

        Country mockCountry = new Country();
        mockCountry.setId(1);
        mockCountry.setName("Croatia");
        when(countryRepo.findCountry("Croatia")).thenReturn(1);
        when(countryRepo.findById(1)).thenReturn(Optional.of(new Country(1, "Croatia")));

        // Act
        holidaysService.addNewHoliday(holidays, "Croatia");

        // Assert
        verify(holidaysRepo, times(1)).save(holidays);
        assertEquals(mockCountry, holidays.getCountry());
    }
}
