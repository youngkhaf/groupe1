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

import com.sir.wallet.controller.WalletController;
import com.sir.wallet.cucumber.CucumberTest;
import com.sir.wallet.model.Wallet;

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

    @When("I DELETE the wallet from the {string} endpoint")
    public void I_DELETE_the_wallet_from_the_endpoint(String s) {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("I PUT the wallet to the {string} endpoint with name {string} and balance {int}")
    public void I_PUT_the_wallet_to_the_endpoint_with_name_and_balance(String s, String s2, int i) {
       
    }


    @When("I POST the wallet to the {string} endpoint")
    public void I_POST_the_wallet_to_the_endpoint(String s) {
        savedWallet = this.walletController.saveWallet(wallet);
    }

    @Given("a wallet with name {string} and balance {int}")
    public void a_wallet_with_name_and_balance(String name, int balance) {
        wallet = new Wallet(name,balance);
    }

    @Then("the response body should contain the wallet")
    public void the_response_body_should_contain_the_wallet() {
        savedWallet != null;
    }

    @When("I GET the wallet from the {string} endpoint")
    public void I_GET_the_wallet_from_the_endpoint(String s) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("the wallet should not be retrievable")
    public void the_wallet_should_not_be_retrievable() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("the response body should contain the updated wallet")
    public void the_response_body_should_contain_the_updated_wallet() {
        // Write code here that turns the phrase above into concrete actions
    }


}
