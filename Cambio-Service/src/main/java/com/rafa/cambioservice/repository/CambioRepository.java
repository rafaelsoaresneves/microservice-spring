package com.rafa.cambioservice.repository;

import com.rafa.cambioservice.model.Cambio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CambioRepository extends JpaRepository<Cambio, Long> {
    Cambio findCambioByFromAndTo(String from, String to);
}
