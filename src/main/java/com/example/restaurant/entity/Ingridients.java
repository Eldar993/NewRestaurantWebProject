package com.example.restaurant.entity;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ingridients")
public class Ingridients {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique=true)
    private String title;

    private int calories;

//   @ManyToMany
//    @JoinTable(
//            name = "Menu_Ingridients",
//            JoinColumns = @JoinColumn(name = "ingridients_id"),
//            inverseJoinColumns = @JoinColumn(name = "menu_id"))
//    private List<Menu> menuList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        Ingridients that = (Ingridients) o;
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
        return "Ingridients{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", calories=" + calories +
                '}';
    }
}
