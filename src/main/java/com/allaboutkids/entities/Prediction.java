package com.allaboutkids.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "predictions")
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nr_of_children", nullable = false)
    private String nrOfChildren;

    @Column(name = "cost", nullable = false)
    private String cost;

    @Column(name = "profit", nullable = false)
    private String profit;

    public Prediction(Long id, String nrOfChildren, String cost, String profit) {
        this.id = id;
        this.nrOfChildren = nrOfChildren;
        this.cost = cost;
        this.profit = profit;
    }

    public Prediction() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNrOfChildren() {
        return nrOfChildren;
    }

    public void setNrOfChildren(String nrOfChildren) {
        this.nrOfChildren = nrOfChildren;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }
}
