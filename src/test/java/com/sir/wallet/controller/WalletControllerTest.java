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
import com.sir.wallet.model.Wallet;
import com.sir.wallet.model.Wallet;
import com.sir.wallet.repository.WalletRepository;
import com.sir.wallet.services.WalletService;
import com.sir.wallet.services.WalletServiceImpl;

import io.cucumber.messages.internal.com.google.common.util.concurrent.ExecutionError;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WalletControllerTest {
    @Autowired
    WalletController walletController;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    MockMvc mockMvc;

    @LocalServerPort
    int port;

    @MockBean
    WalletRepository personService;


    Wallet testWallet = new Wallet();


    @BeforeEach
    void returnsAWallet(){
        testWallet = new Wallet();
        testWallet.setId(10l);
        when(personService.existsById(testWallet.getId())).thenReturn(true);
        when(personService.findById(testWallet.getId())).thenReturn(Optional.of(testWallet));
        when(personService.save(testWallet)).thenReturn(testWallet);
        
    }

    @Test
    void testCreateWallet() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] requestBody = objectMapper.writeValueAsBytes(testWallet);
                        RequestBuilder request = MockMvcRequestBuilders
                .post("/wallets")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON); // "application/json"


        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(response.getStatus() ,HttpStatus.CREATED.value());
        //assertTrue(response.getContentAsString().contains(wallet.getType()));
        
    }

    @Test
    void testUpdateWallet() throws Exception{
        testWallet.setName("modou");
        testWallet.setId(10l);
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] requestBody = objectMapper.writeValueAsBytes(testWallet);
                        RequestBuilder request = MockMvcRequestBuilders
                .put("/wallets")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON); // "application/json"


        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(response.getStatus() ,HttpStatus.CREATED.value());
        
    }

    @Test
    void testGetWalletById() throws Exception{
        testWallet.setId(10l);
                        RequestBuilder request = MockMvcRequestBuilders
                .get("/wallets/" +  testWallet.getId())
                .contentType(MediaType.APPLICATION_JSON); // "application/json"
        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());  
    }

    @Test
    void testDeleteWallet() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] requestBody = objectMapper.writeValueAsBytes(testWallet);
        RequestBuilder request = MockMvcRequestBuilders
                .delete("/wallets")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON); // "application/json"
        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(response.getStatus(),HttpStatus.NO_CONTENT.value());
    }

    @Test
    void testGetAllWallets() throws Exception{
                //Given
                RequestBuilder request = MockMvcRequestBuilders
                .get("/wallets")
                .contentType(MediaType.APPLICATION_JSON); // "application/json"

        when(personService.findAll()).thenReturn(List.of(new Wallet(1000,"test")));

        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
