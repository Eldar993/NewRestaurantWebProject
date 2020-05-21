package com.example.restaurant.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique=true)
    private String title;

    private int calories;

//   @ManyToMany
//    @JoinTable(
//            name = "Menu_Ingredient",
//            JoinColumns = @JoinColumn(name = "ingredient_id"),
//            inverseJoinColumns = @JoinColumn(name = "menu_id"))
//    private List<Menu> menuList;

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

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return id == that.id &&
                calories == that.calories &&
                title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, calories);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", calories=" + calories +
                '}';
    }
}
