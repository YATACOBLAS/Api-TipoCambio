package com.blas.api.tipocambio.repository;

import com.blas.api.tipocambio.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RolRepository extends JpaRepository<Rol,Long> {


    @Query(value = "select * from rol where id_rol=(select id_rol from usuario where email=?1)",nativeQuery = true)
    Rol buscarPorEmail(String email);

}
