package com.okanciftci.cukatify.security.jwt;

import com.okanciftci.cukatify.entities.mongo.User;
import com.okanciftci.cukatify.services.impl.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static com.okanciftci.cukatify.security.SecurityConstants.HEADER_STRING;
import static com.okanciftci.cukatify.security.SecurityConstants.TOKEN_PREFIX;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        try{

            String jwt = getJWTfromRequest(httpServletRequest);

            if(StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)){
                String userId = jwtTokenProvider.getUserId(jwt);
                User user = userDetailService.loadUserById(userId);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        user,null, Collections.emptyList());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }catch(Exception ex){
            logger.error("Could not set user authentication in security context",ex);
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);


    }

    private String getJWTfromRequest(HttpServletRequest httpServletRequest){
        String bearerToken = httpServletRequest.getHeader(HEADER_STRING);

        if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith(TOKEN_PREFIX)){
            return bearerToken.substring(7,bearerToken.length());
        }

        return null;
    }
}
