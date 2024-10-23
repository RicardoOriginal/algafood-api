package com.algaworks.algafood.api.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurante/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @PutMapping
    public void atualizarFoto(@PathVariable Long restauranteId,
                              @PathVariable Long produtoId, @RequestParam MultipartFile arquivo) {

        var nomeArquivo = UUID.randomUUID() + "_" + arquivo.getOriginalFilename();

        var arquivoFoto = Path.of("C:/Users/RicardoLimaOliveira/curso/catalogo", nomeArquivo);

        System.out.println(arquivoFoto);
        System.out.println(arquivo.getContentType());

        try {
            arquivo.transferTo(arquivoFoto);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
