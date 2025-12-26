package com.example.demo.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class JwtAuthenticationFilter {
    private final JwtTokenProvider provider;
    private final CustomUserDetailsService uds;

    public JwtAuthenticationFilter(JwtTokenProvider p, CustomUserDetailsService u){
        this.provider=p; this.uds=u;
    }

    public void doFilter(ServletRequest r, ServletResponse s, FilterChain c)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) r;
        String h = req.getHeader("Authorization");
        if(h!=null && h.startsWith("Bearer ")){
            String t = h.substring(7);
            provider.validateToken(t);
        }
        c.doFilter(r,s);
    }
}