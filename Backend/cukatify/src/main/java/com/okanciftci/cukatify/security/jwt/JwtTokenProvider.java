package com.okanciftci.cukatify.security.jwt;

import com.okanciftci.cukatify.entities.mongo.User;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.okanciftci.cukatify.security.SecurityConstants.EXPIRATION_TIME;
import static com.okanciftci.cukatify.security.SecurityConstants.SECRET;

@Component
public class JwtTokenProvider {


    //Generate Token

    public String generateToken(Authentication authentication){

        User user = (User) authentication.getPrincipal();

        Date now = new Date(System.currentTimeMillis());

        Date expire_date = new Date(now.getTime()+EXPIRATION_TIME);

        String userId = user.getId();

        Map<String,Object> claims = new HashMap<>();
        claims.put("id",(user.getId()));
        claims.put("username",user.getUsername());
        claims.put("fullName",user.getFullName());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expire_date)
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();


    }

    //Validate Token
    public boolean validateToken(String token){

        try{

            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;

        }catch(SignatureException ex) {
            System.out.println("Invalid JWT Signature");
        }catch (MalformedJwtException ex){
            System.out.println("Invalid JWT Token");
        } catch (ExpiredJwtException e){
            System.out.println("Expired JWT Token");
        }catch (UnsupportedJwtException ex){
            System.out.println("Unsupported JWT Token");
        }catch(IllegalArgumentException ex){
            System.out.println("JWT Claims string empty");
        }
        return false;
    }

    public String getUserId(String token){

        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();

        String id = (String) claims.get("id");

        return id;
    }



    //Get User Id from the token

}
