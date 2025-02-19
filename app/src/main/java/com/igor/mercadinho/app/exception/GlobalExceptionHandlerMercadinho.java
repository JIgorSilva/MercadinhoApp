package com.igor.mercadinho.app.exception;

import com.igor.mercadinho.app.exception.global.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerMercadinho {

    @ExceptionHandler(CredenciaisNaoAutorizadaException.class)
    public ResponseEntity<ApiErrorResponse> handlerCredenciaisNaoAutorizadaException(CredenciaisNaoAutorizadaException ex, HttpServletRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "Não autorizado",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(ErroInternoNoServerException.class)
    public ResponseEntity<ApiErrorResponse> erroInternoNoServerException(ErroInternoNoServerException ex,HttpServletRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(SolicitacaoNaoAutorizadaException.class)
    public ResponseEntity<ApiErrorResponse> solicitacaoNaoAutorizadaException(SolicitacaoNaoAutorizadaException ex,HttpServletRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                "Solicitação negada",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(CompraInvalidaItensNullException.class)
    public ResponseEntity<ApiErrorResponse> compraInvalidaItensNullException(CompraInvalidaItensNullException ex, HttpServletRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Ação invalida,",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler(CredenciaisInvalidadasTwoException.class)
    public ResponseEntity<ApiErrorResponse> usuarioOusenhaInvalidos(CredenciaisInvalidadasTwoException ex, HttpServletRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Usuario ou senha invalidos",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(CompraInvalidadeNotFoundPrecoUnitario.class)
    public ResponseEntity<ApiErrorResponse> compraInvalidadeNotFoundPrecoUnitario(CompraInvalidadeNotFoundPrecoUnitario ex, HttpServletRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Ação invalida, não existe preço no produto.",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler(ProdutoResouceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> produtoResouceNotFoundException(ProdutoResouceNotFoundException ex, HttpServletRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Ação invalida, produto não identificado.",
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }
}
