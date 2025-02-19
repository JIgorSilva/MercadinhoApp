package com.igor.mercadinho.app.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.mercadinho.app.dtos.compras.ComprasDtoRequest;
import com.igor.mercadinho.app.dtos.compras.ComprasDtoResponse;
import com.igor.mercadinho.app.exception.global.CredenciaisNaoAutorizadaException;
import com.igor.mercadinho.app.model.Compras;
import com.igor.mercadinho.app.services.ComprasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/compras")
public class CompraController {
    @Autowired
    private ComprasService comprasService;

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Realizar compra", description = "Inicia um processo de compra e retorna os detalhes da transação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Compra realizada com sucesso")
    })
    public ResponseEntity<ComprasDtoResponse> comprar(@RequestBody ComprasDtoRequest comprasDtoRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new CredenciaisNaoAutorizadaException("Token inválido ou expirado");
        }
        String email = authentication.getName();

        Compras compra = comprasService.iniciarCompra(email, comprasDtoRequest);

        // Converter para DTO de resposta
        ComprasDtoResponse comprasDtoResponse = new ComprasDtoResponse(compra);

        return ResponseEntity.ok(comprasDtoResponse);
    }
}
