package com.blas.api.tipocambio.repository;

import com.blas.api.tipocambio.models.Usuario;
import com.blas.api.tipocambio.service.UsuarioService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {


    Optional<Usuario> findOneByEmail(String email);
}
