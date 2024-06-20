package eu.catalkaya.pokertracker.model;

import java.time.LocalDateTime;

public record LatestTransactionDto(int id,
                                   LocalDateTime timestamp,
                                   String playerName,
                                   float amount) {
}
