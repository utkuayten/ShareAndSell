package org.cs320.ozyegin.model;


import jakarta.persistence.*;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long owner_id;
    private int balance;

    public Wallet(){
        super();
    }

    public Wallet(Long id, Long owner_id, int balance) {
        this.id = id;
        this.owner_id = owner_id;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void addBalance(int balance)  { this.balance += balance; }
    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", owner_id=" + owner_id +
                ", balance=" + balance +
                '}';
    }

}
