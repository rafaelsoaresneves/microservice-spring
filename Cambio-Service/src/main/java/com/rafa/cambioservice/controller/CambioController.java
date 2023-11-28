package com.rafa.cambioservice.controller;


import com.rafa.cambioservice.model.Cambio;
import com.rafa.cambioservice.repository.CambioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Tag(name = "Cambio Service")
@RestController
@RequestMapping("cambio-service")
public class CambioController {

    @Autowired
    private Environment environment;
    @Autowired
    private CambioRepository repository;

    private Logger logger = LoggerFactory.getLogger(CambioController.class);

    // http://localhost:8000/api/cambio-service/5/USD/BRL
    @Operation(summary = "Get para conversao de valores")
    @GetMapping(value = "/{amount}/{from}/{to}")
    public Cambio getCambio(
            @PathVariable("amount") BigDecimal amount,
            @PathVariable("from") String from,
            @PathVariable("to") String to) {

        logger.info("getCambio received -> {}, {} and {}", amount, from, to);

        var port = environment.getProperty("local.server.port");
        var cambio = repository.findCambioByFromAndTo(from, to);
        if(cambio == null)
            throw new RuntimeException("Currency Unsuported");

        BigDecimal conversionFactor = cambio.getConversionFactor();
        BigDecimal convertedValue = conversionFactor.multiply(amount);
        cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));
        cambio.setEnviroment(port);

        return cambio;
    }

}
