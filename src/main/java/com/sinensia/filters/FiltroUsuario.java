package com.sinensia.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class FiltroUsuario implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //NECESITAMOS OBJETOS HTTP PARA PODER RECUPERAR LA SESSION O ENVIAR AL LOGIN
        HttpServletRequest peticion = (HttpServletRequest)request;
        HttpServletResponse respuesta = (HttpServletResponse)response;
        //NECESITAMOS SABER LA URL DONDE ESTAMOS NAVEGANDO
        String url = peticion.getRequestURI();
        if(url.contains("smtm")){
            //AQUI DEBEMOS ACTUAR
            HttpSession sesion = peticion.getSession();
            if (sesion.getAttribute("USUARIO") != null) {
                //EXISTE USUARIO Y SE HA VALIDADO
                    chain.doFilter(request, response);
            } else {
                respuesta = (HttpServletResponse)response;
                respuesta.sendRedirect("../login.jsp");
            }
        } else {
            chain.doFilter(request, response);
        }
        
    }

    @Override
    public void destroy() {

    }
    
}