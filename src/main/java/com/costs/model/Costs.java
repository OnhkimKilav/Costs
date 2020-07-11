package com.costs.model;

import org.bson.types.ObjectId;

import java.util.Objects;

public class Costs {
    private ObjectId id;
    private String category;
    private boolean isExpenses;
    private Integer amount;

    public Costs() {
    }

    public Costs(ObjectId id, String category, boolean isExpenses, Integer amount) {
        this.id = id;
        this.category = category;
        this.isExpenses = isExpenses;
        this.amount = amount;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isExpenses() {
        return isExpenses;
    }

    public void setExpenses(boolean expenses) {
        isExpenses = expenses;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getCountedAmount(){
        if (isExpenses)
            return -amount;
        return amount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Costs costs = (Costs) o;
        return isExpenses == costs.isExpenses &&
                category.equals(costs.category) &&
                amount.equals(costs.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, isExpenses, amount);
    }
}
