package com.example.restaurant.service;

import com.example.restaurant.entity.Ingridients;
import com.example.restaurant.entity.User;
import com.example.restaurant.repository.IngridientsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IngridientsService {

    private final IngridientsRepository ingridientsRepository;


    public IngridientsService(IngridientsRepository ingridientsRepository) {
        this.ingridientsRepository = ingridientsRepository;
    }

    public boolean create(Ingridients ingridient) {
        ingridientsRepository.saveAndFlush(ingridient);
        return true;
    }

    public List<Ingridients> printIngridients() {
        List<Ingridients> ingridients = ingridientsRepository.findAll();
        return ingridients;
    }

    public void deleteAll() {
        ingridientsRepository.deleteAll();
    }

    public void deleteIngridient(Long id) {

        Ingridients deleted = ingridientsRepository.findIngridientsById(id);
        ingridientsRepository.delete(deleted);


    }

    public boolean update(Ingridients ingridients) {
        Optional<Ingridients> oldInfo = ingridientsRepository.findById(ingridients.getId());
        ingridientsRepository.saveAndFlush(ingridients);
        return true;
    }
}
