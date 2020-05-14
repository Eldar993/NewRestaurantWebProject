package com.example.restaurant.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Menu Ingridients")
public class Menu_Ingridients {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ingridients_id;

    @Id
    private Long menu_id;

    public Long getIngridients_id() {
        return ingridients_id;
    }

    public void setIngridients_id(Long ingridients_id) {
        this.ingridients_id = ingridients_id;
    }

    public Long getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Long menu_id) {
        this.menu_id = menu_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu_Ingridients that = (Menu_Ingridients) o;
        return ingridients_id.equals(that.ingridients_id) &&
                menu_id.equals(that.menu_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingridients_id, menu_id);
    }

    @Override
    public String toString() {
        return "Menu_Ingridients{" +
                "ingridients_id=" + ingridients_id +
                ", menu_id=" + menu_id +
                '}';
    }
}
