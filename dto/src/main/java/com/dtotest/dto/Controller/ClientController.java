package com.dtotest.dto.Controller;

import com.dtotest.dto.service.dto.AccountSettingsDTO;
import com.dtotest.dto.service.dto.ClientDTO;
import com.dtotest.dto.service.interfaces.ClientService;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
