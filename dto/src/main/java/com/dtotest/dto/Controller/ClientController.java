package com.dtotest.dto.Controller;

import com.dtotest.dto.entity.Client;
import com.dtotest.dto.entity.Holidays;
import com.dtotest.dto.service.dto.AccountSettingsDTO;
import com.dtotest.dto.service.dto.ClientDTO;
import com.dtotest.dto.service.interfaces.ClientService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/client")
@Data
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<ClientDTO> getClients(){
        return clientService.getClients();
    }

    @GetMapping(path = "{id}")
    public ClientDTO getClientById(@PathVariable("id") Integer id){
        return clientService.getClientById(id);
    }
    @PutMapping(path = "{id}")
    public void updateClient(@PathVariable("id") Integer Id,
                                        @RequestParam(required = false) String name,
                                        @RequestParam(required = false) String internalCode,
                                        @RequestParam(required = false) String industry,
                                        @RequestParam(required = false) String country,
                                        @RequestParam(required = false) String city,
                                        @RequestParam(required = false) String streetName,
                                        @RequestParam(required = false) String streetNumber,
                                        @RequestParam(required = false) String zip) {
        clientService.updateClient(Id, name, internalCode, industry, country, city, streetName, streetNumber, zip);
    }
    @PostMapping
    public void addNewClient(@RequestBody Client client){
        clientService.addNewClient(client);
    }
}
