package com.blas.api.tipocambio.repository;

import com.blas.api.tipocambio.models.MonedaDestino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MonedaDestinoRepository extends JpaRepository<MonedaDestino,Long> {

    @Query(value = "select * from moneda_destino where descripcion=?1",nativeQuery = true)
    Optional<MonedaDestino> BuscarPorDescripcion(String descripcion);

}
