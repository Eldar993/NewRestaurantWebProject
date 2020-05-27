package com.example.restaurant.controller;

import com.example.restaurant.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BillController {
    @Autowired
    private BillService billService;
}
