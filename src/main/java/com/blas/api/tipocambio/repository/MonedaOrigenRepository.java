package com.blas.api.tipocambio.repository;

import com.blas.api.tipocambio.models.MonedaOrigen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MonedaOrigenRepository extends JpaRepository<MonedaOrigen,Long> {
    @Query(value = "select * from moneda_origen where descripcion=?1",nativeQuery = true)
    Optional<MonedaOrigen> BuscarPorDescripcion(String descripcion);

}
