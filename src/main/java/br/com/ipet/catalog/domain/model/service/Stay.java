package br.com.ipet.catalog.domain.model.service;

import java.time.LocalDateTime;

public interface Stay {
    public LocalDateTime checkIn();
    public LocalDateTime checkOut();
}
