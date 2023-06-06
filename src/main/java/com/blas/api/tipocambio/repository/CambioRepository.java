package com.blas.api.tipocambio.repository;

import com.blas.api.tipocambio.models.Cambio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CambioRepository extends JpaRepository<Cambio,Long> {

    @Query(value = "{call GUARDAR_CAMBIO(:param1,:param2,:param3)}",nativeQuery = true)
    Optional<Cambio> guardarCambio(@Param("param1") String Origen,@Param("param2") String Destino,@Param("param3") double valor);
    @Query(
            value = "select * from cambio where id_moneda_origen=?1 and id_moneda_destino=?2",nativeQuery = true)
    Optional<Cambio> buscarTipoCambio(String monedaOrigen,String monedaDestino);
}
