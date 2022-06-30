package com.ufes.delivery.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventoPedido {

    private final LocalDateTime dataHora;
    private final String mensagem;

    public EventoPedido(LocalDateTime dataHora, String mensagem) {
        this.dataHora = dataHora;
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "Evento no pedido: " + dataHora.format(formatter) + "; " + mensagem;
    }

}
