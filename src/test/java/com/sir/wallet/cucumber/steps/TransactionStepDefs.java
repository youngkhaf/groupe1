package com.sir.wallet.cucumber.steps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.sir.wallet.controller.TransactionController;
import com.sir.wallet.controller.WalletController;
import com.sir.wallet.cucumber.CucumberTest;
import com.sir.wallet.model.Wallet;
import com.sir.wallet.repository.TransactionRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
import org.junit.runner.RunWith;
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestConfiguration.class)
public class TransactionStepDefs {

    Wallet wallet = new Wallet();
    


    @Autowired
    TransactionRepository transactionRepository;


    @Autowired
    TransactionController transactionController;

    @Autowired
    WalletController walletController;



    @Then("the wallet with ID {int} should have a balance of {int}")
    public void the_wallet_with_ID_should_have_a_balance_of(int id, int balance) {
        Wallet wallet = this.walletController.getWallet(id);
        assertEquals(wallet.getBalance(), balance);
        
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(int i) {
        
    }

    @When("I POST a transaction with wallet ID {int} and amount {int} and type {string} to the {string} endpoint")
    public void I_POST_a_transaction_with_wallet_ID_and_amount_and_type_to_the_endpoint(int walletId, int amount, String type, String endpoint) {
        Wallet wallet =new Wallet();
        wallet.setId(walletId);
        Transaction transaction = new Transaction(wallet,amount,type);
        this.transactionController.createTransaction(transaction);
    }

    @Given("a wallet with ID {int} and name {string} and balance {int}")
    public void a_wallet_with_ID_and_name_and_balance(int id, String name, int balance) {
        wallet = new Wallet(id,name,balance);
        
    }








}
