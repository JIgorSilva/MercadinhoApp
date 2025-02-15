package com.igor.mercadinho.app.controller;

import com.igor.mercadinho.app.dtos.compras.ComprasDtoRequest;
import com.igor.mercadinho.app.dtos.compras.ComprasDtoResponse;
import com.igor.mercadinho.app.model.Compras;
import com.igor.mercadinho.app.services.ComprasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compras")
public class CompraController {
    @Autowired
    private ComprasService comprasService;

    @PostMapping
    @SecurityRequirement(name = "bearerAuth") // Exige autenticação JWT
    @Operation(summary = "Obter recurso de exemplo", description = "Retorna um recurso de exemplo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"), @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "403", description = "Acesso proibido"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<ComprasDtoResponse> comprar(@RequestBody ComprasDtoRequest comprasDtoRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Obtém o e-mail do usuário autenticado

        System.out.println("✅ Usuário autenticado: " + email);
        Compras compra = comprasService.iniciarCompra(email, comprasDtoRequest);

        // Converter para DTO de resposta
        ComprasDtoResponse comprasDtoResponse = new ComprasDtoResponse(compra);

        return ResponseEntity.ok(comprasDtoResponse);
    }
}
