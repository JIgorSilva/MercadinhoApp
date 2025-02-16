package com.igor.mercadinho.app.exception.global;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estrutura padrão para respostas de erro da API")
public class ApiErrorResponse {
    
    @Schema(description = "Momento em que o erro ocorreu", example = "2025-02-15T10:00:00Z")
    private LocalDateTime timestamp;

    @Schema(description = "Código HTTP do erro", example = "401")
    private int status;

    @Schema(description = "Descrição do erro", example = "Não autorizado")
    private String error;

    @Schema(description = "Mensagem detalhada sobre o erro", example = "Token inválido ou expirado")
    private String message;

    @Schema(description = "Caminho da requisição que gerou o erro", example = "/compras")
    private String path;

    public ApiErrorResponse(int status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
