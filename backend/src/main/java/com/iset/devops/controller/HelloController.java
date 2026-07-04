package com.iset.devops.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    // Endpoint simple pour verifier que le conteneur backend fonctionne correctement
    @GetMapping("/api/hello")
    public Map<String, String> hello() {
        Map<String, String> reponse = new HashMap<>();
        reponse.put("message", "Bonjour depuis le backend Spring Boot conteneurise !");
        reponse.put("statut", "OK");
        return reponse;
    }
}
