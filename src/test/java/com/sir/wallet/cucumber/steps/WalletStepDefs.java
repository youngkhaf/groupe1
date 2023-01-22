package com.sir.wallet.cucumber.steps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sir.wallet.controller.TransactionController;
import com.sir.wallet.controller.WalletController;
import com.sir.wallet.cucumber.CucumberTest;
import com.sir.wallet.model.Wallet;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.runner.RunWith;
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestConfiguration.class)
public class WalletStepDefs {
    Wallet wallet ;
    Wallet savedWallet;

    @Autowired
    WalletController walletController;
    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    MockMvc mockMvc;

    @LocalServerPort
    int port;



    @Autowired
    TransactionController transactionController;




    MockHttpServletResponse response;

    @When("I DELETE the wallet from the {string} endpoint")
    public void I_DELETE_the_wallet_from_the_endpoint(String endpoint) throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
        .put(endpoint)
        .contentType(MediaType.APPLICATION_JSON); // "application/json"
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        response = mvcResult.getResponse();
        
    }

    @When("I PUT the wallet to the {string} endpoint with name {string} and balance {int}")
    public void I_PUT_the_wallet_to_the_endpoint_with_name_and_balance(String endpoint, String s2, int i) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] requestBody = objectMapper.writeValueAsBytes(wallet);
                        RequestBuilder request = MockMvcRequestBuilders
                .put(endpoint)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON); // "application/json"


        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        response = mvcResult.getResponse();

       
    }


    @When("I POST the wallet to the {string} endpoint")
    public void I_POST_the_wallet_to_the_endpoint(String endpoint) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] requestBody = objectMapper.writeValueAsBytes(wallet);
                        RequestBuilder request = MockMvcRequestBuilders
                .post(endpoint)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON); // "application/json"


        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        response = mvcResult.getResponse();
    }

    @Given("a wallet with name {string} and balance {int}")
    public void a_wallet_with_name_and_balance(String name, int balance) {
        wallet = new Wallet(name,balance);
    }

    @Then("the response body should contain the wallet")
    public void the_response_body_should_contain_the_wallet() throws UnsupportedEncodingException {
        response.getContentAsString().contains(wallet.getName());
    }

    @When("I GET the wallet from the {string} endpoint")
    public void I_GET_the_wallet_from_the_endpoint(String endpoint)throws Exception {
                        RequestBuilder request = MockMvcRequestBuilders
                .get(endpoint)
                .contentType(MediaType.APPLICATION_JSON); // "application/json"


        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        response = mvcResult.getResponse();
    }

    @Then("the wallet should not be retrievable")
    public void the_wallet_should_not_be_retrievable() {
        assertThrows(IllegalArgumentException.class, () -> this.walletController.getWallet(wallet.getId()));
    }

    @Then("the response body should contain the updated wallet")
    public void the_response_body_should_contain_the_updated_wallet() throws Exception {
        assertTrue(response.getContentAsString().contains(wallet.getName()));
    }


}
