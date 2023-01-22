package com.sir.wallet.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

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

import com.sir.wallet.model.Transaction;
import com.sir.wallet.services.TransactionService;
import com.sir.wallet.services.TransactionServiceImpl;

import io.cucumber.messages.internal.com.google.common.util.concurrent.ExecutionError;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TransactionControllerTest {
    @Autowired
    TransactionController personController;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    MockMvc mockMvc;

    @LocalServerPort
    int port;

    @MockBean
    TransactionServiceImpl personService;

    @Test
    void testCreateTransaction() {


    }

    @Test
    void testGetAllTransactions() throws Exception{
                //Given
                RequestBuilder request = MockMvcRequestBuilders
                .get("/api/transactions/transactions")
                .contentType(MediaType.APPLICATION_JSON); // "application/json"

        when(personService.getAllTransactions()).thenReturn(List.of(new Transaction(1000,"test")));

        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());

        assertTrue(response.getContentAsString().contains("test"));

    }
}
