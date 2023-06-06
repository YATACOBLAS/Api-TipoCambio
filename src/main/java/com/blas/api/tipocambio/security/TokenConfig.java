package com.blas.api.tipocambio.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.*;

public class TokenConfig {

    private final static String ACCESS_TOKEN_SECRET ="KdIZ76UYYbSuyLeVS1jieBoWbCiGDjqdFWtXeNsRADM=";
    private final static Long ACCESS_TOKEN_SECONDS = 2_592_000L;


    public static String createToken(String nombre, String email){
            long time_expiration=ACCESS_TOKEN_SECONDS*1_000;
            Date date_expiration=new Date(System.currentTimeMillis()+time_expiration);

            Map<String, Object> mapdata = new HashMap<>();
                mapdata.put("nombre",nombre);
    return Jwts.builder()
                .setSubject(email)
                .setExpiration(date_expiration)
                .addClaims(mapdata)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();

    }

    //recibe el token para validar
    public static UsernamePasswordAuthenticationToken authenticationToken(String token){
        try {
            Claims claims =Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String email = claims.getSubject();
            //devolvemos un passwordcon el usuario
            return new UsernamePasswordAuthenticationToken(email,null, Collections.emptyList());

        }catch (JwtException jwtException){
            return null;
        }
     }
}
