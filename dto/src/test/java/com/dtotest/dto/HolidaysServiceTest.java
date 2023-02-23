package com.dtotest.dto;

import com.dtotest.dto.dao.CountryRepo;
import com.dtotest.dto.dao.HolidaysRepo;
import com.dtotest.dto.entity.Country;
import com.dtotest.dto.entity.Holidays;
import com.dtotest.dto.service.dto.HolidaysDTO;
import com.dtotest.dto.service.interfaces.HolidaysService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;


import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class HolidaysServiceTest {

    @Mock
    private HolidaysRepo holidaysRepo;

    @Mock
    private CountryRepo countryRepo;

    @InjectMocks
    private HolidaysService holidaysService;

    private Holidays holiday;

    @Test
    public void testGetHolidaysById() {
        // Create test data
        holiday = new Holidays();
        holiday.setName("Christmas Day");
        holiday.setDate(new Date(2023,12,25));
        holidaysService.getHolidaysRepo().save(holiday);

        // Call method being tested
        HolidaysDTO holidayDTO = holidaysService.getHolidaysById(holiday.getId());

        // Verify results
        assertEquals("Christmas Day", holidayDTO.getName());
        assertEquals(LocalDate.of(2023, 12, 25), holidayDTO.getDate());
    }
    @Test
    public void testAddNewHoliday() {
        // Arrange
        Holidays holidays = new Holidays();
        holidays.setName("Independence Day");
        holidays.setDate(new Date(2023,5,3));

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
