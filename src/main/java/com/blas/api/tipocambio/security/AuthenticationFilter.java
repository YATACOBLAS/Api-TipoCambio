package com.blas.api.tipocambio.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    //PRIMER FILTRO DE authenticacion
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {
        AuthCredential authCredential = new AuthCredential();
        try {
            authCredential= new ObjectMapper().readValue(request.getReader(),AuthCredential.class);
        }catch (IOException io) {}


        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                authCredential.getEmail(),
                authCredential.getPassword(),
                Collections.emptyList()
        );

        return getAuthenticationManager().authenticate(usernamePAT);
    }

    //cuando la autenticacion suceda este método se ejecuta
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {


      UserDetailsImpl userDetails= (UserDetailsImpl) authResult.getPrincipal();

      String token= TokenConfig.createToken(userDetails.getNombre(),userDetails.getUsername());
      response.addHeader("Authorization" ,"Bearer "+token);
      response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }

}
