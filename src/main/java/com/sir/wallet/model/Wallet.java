package com.sir.wallet.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Entity
@AllArgsConstructor
@Getter
@Setter
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private long balance;

    // Constructors, getters, and setters

    public Wallet(String name,long balance){
        this.name = name;
        this.balance = balance;
    }

    public Wallet() {
        this.name = "test";
        this.balance = 100;
    }

    public Wallet(long id2, String name, int i) {
        this.id = id2;
        this.name = name;
        this.balance = i;
    }

    public Wallet(int i, String string) {
    }
}
