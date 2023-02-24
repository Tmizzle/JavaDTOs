package com.dtotest.dto.service.interfaces;

import com.dtotest.dto.dao.ClientRepo;
import com.dtotest.dto.entity.AccountSettings;
import com.dtotest.dto.entity.Client;
import com.dtotest.dto.entity.Holidays;
import com.dtotest.dto.service.dto.ClientDTO;
import com.dtotest.dto.service.dto.HolidaysDTO;
import com.dtotest.dto.service.mapper.ClientMapper;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Data
public class ClientService {

    private final ClientMapper clientMapper;
    private final ClientRepo clientRepo;

    public List<ClientDTO> getClients() {
        List<Client> client = clientRepo.findAll();
        List<ClientDTO> clientDTOs = clientMapper.entitiesToDTOs(client);
        return clientDTOs;
    }

    public ClientDTO getClientById(Integer id) {
        Client client = clientRepo.findById(id).orElseThrow(() -> new IllegalStateException("" +
                "client with id " + id + " does not exist"));
        return clientMapper.entityToDTO(client);
    }

    @Transactional
    public void updateClient(Integer Id,
                             String name,
                             String internalCode,
                             String country,
                             String city,
                             String streetName,
                             String streetNumber,
                             String zip) {
        Client client = clientRepo.findById(Id).orElseThrow(() -> new IllegalStateException("" +
                "client with id " + Id + " does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(client.getName(), name)) {
            client.setName(name);
        }
        if (internalCode != null && internalCode.length() > 0 && !Objects.equals(client.getInternalCode(), internalCode)) {
            client.setInternalCode(internalCode);
        }
        if (country != null && country.length() > 0 && !Objects.equals(client.getCountry(), country)) {
            client.setCountry(country);
        }
        if (city != null && city.length() > 0 && !Objects.equals(client.getCity(), city)) {
            client.setCity(city);
        }
        if (streetName != null && streetName.length() > 0 && !Objects.equals(client.getStreetName(), streetName)) {
            client.setStreetName(streetName);
        }
        if (streetNumber != null && streetNumber.length() > 0 && !Objects.equals(client.getStreetNumber(), streetNumber)) {
            client.setStreetNumber(streetNumber);
        }
        if (zip != null && zip.length() > 0 && !Objects.equals(client.getZip(), zip)) {
            client.setZip(zip);
        }

    }
}
