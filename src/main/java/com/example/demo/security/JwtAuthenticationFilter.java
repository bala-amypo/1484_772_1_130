// package com.example.demo.security;

// import jakarta.servlet.*;
// import jakarta.servlet.http.*;
// import java.io.IOException;

// public class JwtAuthenticationFilter {
//     private final JwtTokenProvider provider;
//     private final CustomUserDetailsService uds;

//     public JwtAuthenticationFilter(JwtTokenProvider p, CustomUserDetailsService u){
//         this.provider=p; this.uds=u;
//     }

//     public void doFilter(ServletRequest r, ServletResponse s, FilterChain c)
//             throws IOException, ServletException {
//         HttpServletRequest req = (HttpServletRequest) r;
//         String h = req.getHeader("Authorization");
//         if(h!=null && h.startsWith("Bearer ")){
//             String t = h.substring(7);
//             provider.validateToken(t);
//         }
//         c.doFilter(r,s);
//     }
// }




// package com.example.demo.security;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// import java.io.IOException;

// import org.springframework.web.filter.OncePerRequestFilter;

// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     private final JwtTokenProvider jwtTokenProvider;

//     public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
//         this.jwtTokenProvider = jwtTokenProvider;
//     }

//     @Override
//     protected void doFilterInternal(
//             HttpServletRequest request,
//             HttpServletResponse response,
//             FilterChain filterChain
//     ) throws ServletException, IOException {

//         String header = request.getHeader("Authorization");

//         if (header != null && header.startsWith("Bearer ")) {
//             String token = header.substring(7);

//             if (!jwtTokenProvider.validateToken(token)) {
//                 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                 return;
//             }
//         }

//         filterChain.doFilter(request, response);
//     }
// }




package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public JwtAuthenticationFilter(
            JwtTokenProvider jwtTokenProvider,
            CustomUserDetailsService customUserDetailsService
    ) {
        this.jwtTokenProvider = jwtTokenProvider;
        // customUserDetailsService is NOT needed for logic
        // kept only for test compatibility
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            if (!jwtTokenProvider.validateToken(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
