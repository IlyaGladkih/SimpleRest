package com.example.demo.security;

import com.example.demo.util.JWTUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class JwtFilter extends GenericFilterBean {

    private static final String AUTHORIZATION = "Authorization";

    private final JwtProvider jwtProvider;

    @Autowired
    public JwtFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        String tokenFromRequest = getTokenFromRequest((HttpServletRequest) servletRequest);

        if (tokenFromRequest!=null&& jwtProvider.validateToken(tokenFromRequest)){
            Claims claims = jwtProvider.getClaims(tokenFromRequest);
            JwtAuthentification authentification = JWTUtil.generate(claims);
            authentification.setAuthentificated(true);
            SecurityContextHolder.getContext().setAuthentication(authentification);
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    private String getTokenFromRequest(HttpServletRequest request){
        String header = request.getHeader(AUTHORIZATION);

        if(StringUtils.hasText(header)&&header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
