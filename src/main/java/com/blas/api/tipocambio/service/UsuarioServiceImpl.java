package com.blas.api.tipocambio.service;

import com.blas.api.tipocambio.models.Usuario;
import com.blas.api.tipocambio.repository.UsuarioRepository;
import com.blas.api.tipocambio.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    @Autowired
    private UsuarioRepository repository;


    @Override
    public List<Usuario> listar() {
        return repository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = repository.findOneByEmail(email)
                .orElseThrow(() -> new RuntimeException("El email : " + email + ", no existe."));

        return new UserDetailsImpl(usuario);
    }


}
