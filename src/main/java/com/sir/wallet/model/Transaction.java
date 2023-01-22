package com.sir.wallet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Entity
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Wallet wallet;

    private long amount;
    private String type;

    // Constructors, getters, and setters

    public Transaction(Wallet wallet,int amount,String type){
        this.wallet = wallet;
        this.amount = amount;
        this.type = type;
    }

    public Transaction(){
        this.amount = 10;
        this.type = "test";
        this.wallet = new Wallet("Test", 0);
    }
}
