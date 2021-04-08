package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.service.ClientService;
import com.example.demo.service.ClientServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;
    private static final Logger logger = LogManager.getLogger(ClientServiceImpl.class);
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }



    @PostMapping(value = "/clients")
    public ResponseEntity<?> create(@RequestBody Client client)throws HttpServerErrorException {
        try {
            if (!client.isValid())
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
        catch (HttpClientErrorException exeption){
            logger.error("bad request was faced.");
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        clientService.create(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/clients")
    public ResponseEntity<List<Client>> read() throws  HttpServerErrorException{
        final List<Client> clients = clientService.readAll();
        try {
            if (clients == null || clients.isEmpty())
                throw new HttpClientErrorException(HttpStatus.NO_CONTENT);
        }
        catch (HttpClientErrorException exeption){
            logger.error("there was no clients");
            throw  new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<Client> read(@PathVariable(name = "id") int id) throws HttpServerErrorException {

        final Client client = clientService.read(id);
        try{
            if(client == null)
                throw new HttpClientErrorException(HttpStatus.NO_CONTENT);
        }
        catch (HttpClientErrorException exeption){
            logger.error("there was no client ");
            throw  new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return client != null ? new ResponseEntity<>(client, HttpStatus.OK):
                new ResponseEntity<>(client, HttpStatus.NO_CONTENT);


    }

    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Client client) throws HttpServerErrorException {
        final boolean updated = clientService.update(client, id);
        try{
            if(updated == false)
                throw new HttpClientErrorException(HttpStatus.NOT_MODIFIED);
        }
        catch (HttpClientErrorException exeption){
            logger.error("client can not be modified");
            throw  new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = clientService.delete(id);
        try{
            if(deleted == false)
                throw new HttpClientErrorException(HttpStatus.NOT_MODIFIED);
        }
        catch (HttpClientErrorException exeption){
            logger.error("client can not be deleted");
            throw  new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}