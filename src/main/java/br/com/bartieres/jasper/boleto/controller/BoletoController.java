package br.com.bartieres.jasper.boleto.controller;

import br.com.bartieres.jasper.boleto.service.BoletoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/reports")
public class BoletoController {

    private BoletoService boletoService;

    public BoletoController(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @GetMapping
    public void generate() {
        boletoService.generate();
    }
}
