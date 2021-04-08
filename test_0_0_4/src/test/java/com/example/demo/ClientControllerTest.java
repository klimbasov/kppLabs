package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.demo.model.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientControllerTest extends AbstractTest {
    @Autowired
    ObjectMapper objectMapper;
    Client client = new Client();
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    @Test
    public void testGetAllClients() throws Exception {
        client.setName("jTestName");
        client.setEmail("jTestMail");
        client.setPhone("jTestPhone");
        String uri = "/clients";
        mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(client)))
                .andReturn();

        MvcResult mvcResult =  mvc.perform(MockMvcRequestBuilders.get(uri))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
    @Test
    public void testPostsmth() throws Exception {
        //Client client = new Client();
        client.setName("jTestName");
        client.setEmail("jTestMail");
        client.setPhone("jTestPhone");
        String uri = "/clients";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(client)))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }
}
