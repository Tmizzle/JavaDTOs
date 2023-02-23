package com.dtotest.dto;

import com.dtotest.dto.dao.CountryRepo;
import com.dtotest.dto.dao.HolidaysRepo;
import com.dtotest.dto.entity.Country;
import com.dtotest.dto.entity.Holidays;
import com.dtotest.dto.service.interfaces.HolidaysService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;


import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@EnableJpaRepositories(basePackages = "com.dtotest.dto.dao")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class HolidaysServiceTest {

    @Mock
    private HolidaysRepo holidaysRepo;

    @Mock
    private CountryRepo countryRepo;

    @InjectMocks
    private HolidaysService holidaysService;

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
