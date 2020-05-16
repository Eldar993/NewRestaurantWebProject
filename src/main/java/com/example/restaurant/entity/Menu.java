package com.example.restaurant.entity;



import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dish_type_id")
    private DishType dishType;

    private int price;

    @Column(unique = true)
    private String dish;


    private int weight;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DishType getdishType() {
        return dishType;
    }

    public void setdishType(DishType dishType) {
        this.dishType = dishType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return id == menu.id &&
                dishType == menu.dishType &&
                price == menu.price &&
                weight == menu.weight &&
                dish.equals(menu.dish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dishType, price, dish, weight);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", dishType=" + dishType +
                ", price=" + price +
                ", dish='" + dish + '\'' +
                ", weight=" + weight +
                '}';
    }
}
