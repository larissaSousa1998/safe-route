package br.com.polaris.safe.route.controller;

import br.com.polaris.safe.route.repository.DataViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/datas-viagem")
public class DataViagemController {

    @Autowired
    private DataViagemRepository repository;


}