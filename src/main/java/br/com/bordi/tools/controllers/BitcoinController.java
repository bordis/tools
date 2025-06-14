package br.com.bordi.tools.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bordi.tools.services.BitcoinService;

@RestController
public class BitcoinController {

    @Autowired
    private BitcoinService bitcoinService;
    
    @GetMapping("/bitcoin")
    public String getBitcoinPrice() {
        return bitcoinService.getBitcoinPrice();
    }
        


}
