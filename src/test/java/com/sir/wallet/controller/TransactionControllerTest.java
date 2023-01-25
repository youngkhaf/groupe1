package com.sir.wallet.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sir.wallet.model.Transaction;
import com.sir.wallet.model.Wallet;
import com.sir.wallet.repository.TransactionRepository;
import com.sir.wallet.services.TransactionService;
import com.sir.wallet.services.TransactionServiceImpl;

import io.cucumber.messages.internal.com.google.common.util.concurrent.ExecutionError;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TransactionControllerTest {
    @Autowired
    TransactionController transactionController;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    MockMvc mockMvc;

    @LocalServerPort
    int port;

    @MockBean
    TransactionRepository personService;


    Transaction testTransaction = new Transaction();


    @BeforeEach
    void returnsATransaction(){
        testTransaction = new Transaction();
        testTransaction.setId(10l);
        when(personService.existsById(testTransaction.getId())).thenReturn(true);
        when(personService.findById(testTransaction.getId())).thenReturn(Optional.of(testTransaction));
        when(personService.save(testTransaction)).thenReturn(testTransaction);
        
    }

    @Test
    void testCreateTransaction() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] requestBody = objectMapper.writeValueAsBytes(testTransaction);
                        RequestBuilder request = MockMvcRequestBuilders
                .post("/transactions")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON); // "application/json"


        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(response.getStatus() ,HttpStatus.CREATED.value());
        //assertTrue(response.getContentAsString().contains(transaction.getType()));
        
    }

    @Test
    void testUpdateTransaction() throws Exception{
        testTransaction.setType("withdraw");
        testTransaction.setId(10l);
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] requestBody = objectMapper.writeValueAsBytes(testTransaction);
                        RequestBuilder request = MockMvcRequestBuilders
                .put("/transactions")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON); // "application/json"


        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(response.getStatus() ,HttpStatus.CREATED.value());
        
    }

    @Test
    void testGetTransactionById() throws Exception{
        testTransaction.setId(10l);
                        RequestBuilder request = MockMvcRequestBuilders
                .get("/transactions/" +  testTransaction.getId())
                .contentType(MediaType.APPLICATION_JSON); // "application/json"
        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertTrue(response.getContentAsString().contains(testTransaction.getType()));   
    }

    @Test
    void testDeleteTransaction() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] requestBody = objectMapper.writeValueAsBytes(testTransaction);
        RequestBuilder request = MockMvcRequestBuilders
                .delete("/transactions")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON); // "application/json"
        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(response.getStatus(),HttpStatus.NO_CONTENT.value());
    }

    @Test
    void testGetAllTransactions() throws Exception{
                //Given
                RequestBuilder request = MockMvcRequestBuilders
                .get("/transactions")
                .contentType(MediaType.APPLICATION_JSON); // "application/json"

        when(personService.findAll()).thenReturn(List.of(new Transaction(1000,"test")));

        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());

        assertTrue(response.getContentAsString().contains("test"));
    }
}
