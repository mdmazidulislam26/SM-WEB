package com.mazid.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
// A filter class to validate the JWT token in the request
public class jwtValidator extends OncePerRequestFilter {
    // Overridden method to process each HTTP request once for JWT validation
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Get the JWT token from the request header
        String jwt = request.getHeader(JwtConstant.JWT_HEADER);
        // If JWT token exists in the header
        if (jwt != null){
            try{
                // Extract email from the JWT token using JwtProvider utility
                String email = JwtProvider.getEmailFromJwtToken(jwt);
                // Create a list of granted authorities (currently empty, as this example doesn't use roles/permissions)
                List<GrantedAuthority> authorities = new ArrayList<>();
                // Create an Authentication object using the email extracted from JWT (no password or authorities in this case)
                Authentication authentication = new UsernamePasswordAuthenticationToken(email,null,authorities);
                // Set the Authentication object in the SecurityContext, which manages user authentication for the application
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }catch (Exception e){
                // If there is an error (e.g., invalid JWT token), throw a BadCredentialsException
                throw new BadCredentialsException("invalid token...");
            }
        }
        // Continue with the filter chain to allow further processing of the request or pass it to the next filter
        filterChain.doFilter(request,response);
    }
}
