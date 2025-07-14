package com.storedint.demo.endpoint.rest.controller.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

@RestController
public class StoredIntController {

    private static final String FILE_PATH = "/tmp/stored-int.txt"; // compatible AWS Lambda

    @GetMapping("/stored-int")
    public String getStoredInt() {
        try {
            File file = new File(FILE_PATH);

            if (file.exists()) {
                // Lire contenu existant
                String content = Files.readString(Path.of(FILE_PATH));
                return "{\"value\":" + content + "}";
            } else {
                // Créer fichier avec un nombre aléatoire
                int random = new Random().nextInt(1000); // 0 à 999
                Files.writeString(Path.of(FILE_PATH), String.valueOf(random));
                return "{\"value\":" + random + "}";
            }
        } catch (IOException e) {
            return "{\"error\":\"" + e.getMessage() + "\"}";
        }
    }
}
