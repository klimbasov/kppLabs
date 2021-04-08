package com.example.demo.service;

import com.example.demo.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class ClientServiceImpl implements ClientService {


    private static final Map<Integer, Client> CLIENT_REPOSITORY_MAP = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(ClientServiceImpl.class);
    private static final Stack<Integer> FREE_SPACE_STACK = new Stack<>();
    private static final AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();


    @Override
    public void create(Client client)  {    //сюда пихнуть
        logger.info("mapping element");
        if(FREE_SPACE_STACK.isEmpty()) {
            client.setId(CLIENT_ID_HOLDER.incrementAndGet());
        }
        else {
            client.setId(FREE_SPACE_STACK.pop().intValue());
        }
        CLIENT_REPOSITORY_MAP.put(client.getId(), client);
    }

    @Override
    public List<Client> readAll() { //сюда пихнуть
        logger.info("reading request");
        return new ArrayList<>(CLIENT_REPOSITORY_MAP.values());
    }

    @Override
    public Client read(int id) {    //сюда пихнуть
        logger.info("reading request");
        return CLIENT_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Client client, int id) {
        logger.info("updating resource");
        if (CLIENT_REPOSITORY_MAP.containsKey(id)) {
            client.setId(id);
            CLIENT_REPOSITORY_MAP.put(id, client);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) { //сюда пихнуть
        logger.info("deleting resource");
        if(CLIENT_REPOSITORY_MAP.remove(id) != null) {
            FREE_SPACE_STACK.push(id);
            return true;
        }
        return false;
    }
}











