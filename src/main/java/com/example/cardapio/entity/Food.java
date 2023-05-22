package com.example.cardapio.entity;

import com.example.cardapio.DTO.FoodRequestDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Table(name = "foods")
@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String image;
    private Integer price;
    private String description;

    public Food(FoodRequestDTO data){
        this.image = data.image();
        this.price = data.price();
        this.title = data.title();
        this.description = data.description();
    }

    public Food() {
    }

    public Food(Long id, String title, String image, Integer price, String description) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(getTitle(), food.getTitle()) && Objects.equals(getImage(), food.getImage()) && Objects.equals(getPrice(), food.getPrice()) && Objects.equals(getDescription(), food.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getImage(), getPrice(), getDescription());
    }
}
