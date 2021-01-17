package com.epam.candy.entity;

import java.util.Objects;

public class Good extends Entity{
    private Long id;
    private String name;
    private String description;
    private String pictureUrl;
    private Double weight;
    private Integer price;
    private Category category;
    private String ingredients;
    private Integer storagePeriod;

    public Good(Long id, String name, String description,
                String pictureUrl, Double weight, Integer price,
                Category category, String ingredients, Integer storagePeriod) {
        this.id = id;
        this.name = name;
        this.description = description.trim();
        this.pictureUrl = pictureUrl;
        this.weight = weight;
        this.price = price;
        this.category = category;
        this.ingredients = ingredients.trim();
        this.storagePeriod = storagePeriod;
    }

    public Good(String name, String description, String pictureUrl,
                Double weight, Integer price, Category category,
                String ingredients, Integer storagePeriod) {
        this.name = name;
        this.description = description;
        this.pictureUrl = pictureUrl;
        this.weight = weight;
        this.price = price;
        this.category = category;
        this.ingredients = ingredients;
        this.storagePeriod = storagePeriod;
    }

    public Good() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getStoragePeriod() {
        return storagePeriod;
    }

    public void setStoragePeriod(Integer storagePeriod) {
        this.storagePeriod = storagePeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Good good = (Good) o;
        return name.equals(good.name) &&
                Objects.equals(description, good.description) &&
                weight.equals(good.weight) &&
                price.equals(good.price) &&
                category.equals(good.category) &&
                Objects.equals(ingredients, good.ingredients) &&
                storagePeriod.equals(good.storagePeriod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, price, category, storagePeriod);
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", category=" + category +
                ", ingredients='" + ingredients + '\'' +
                ", storagePeriod=" + storagePeriod +
                '}';
    }
}
