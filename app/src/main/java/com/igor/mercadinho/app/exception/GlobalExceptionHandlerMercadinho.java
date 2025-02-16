package com.igor.mercadinho.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.igor.mercadinho.app.exception.global.ApiErrorResponse;
import com.igor.mercadinho.app.exception.global.CredenciaisNaoAutorizadaException;
import com.igor.mercadinho.app.exception.global.ErroInternoNoServerException;
import com.igor.mercadinho.app.exception.global.SolicitacaoNaoAutorizadaException;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestControllerAdvice
public class GlobalExceptionHandlerMercadinho {

    @ExceptionHandler(CredenciaisNaoAutorizadaException.class)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Não autorizado", useReturnTypeSchema = true)
    })
    public ResponseEntity<ApiErrorResponse> handlerCredenciaisNaoAutorizadaException(CredenciaisNaoAutorizadaException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "Não autorizado",
                ex.getMessage(),
                "/compras"
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(ErroInternoNoServerException.class)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "500", description = "Erro interno", useReturnTypeSchema = true)
    })
    public ResponseEntity<ApiErrorResponse> erroInternoNoServerException(ErroInternoNoServerException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno",
                ex.getMessage(),
                "/compras"
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(SolicitacaoNaoAutorizadaException.class)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "403", description = "Solicitação negada", useReturnTypeSchema = true)
    })
    public ResponseEntity<ApiErrorResponse> solicitacaoNaoAutorizadaException(Exception ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Solicitação negada",
                ex.getMessage(),
                "/compras"
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
