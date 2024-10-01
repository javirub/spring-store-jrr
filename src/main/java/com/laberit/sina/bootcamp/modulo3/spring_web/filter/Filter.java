package com.laberit.sina.bootcamp.modulo3.spring_web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
public class Filter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        // Pasar la petición por la cadena de filtros
        filterChain.doFilter(request, responseWrapper);

        // Parsea el cuerpo de la respuesta a la clase especificada y haz algo con ella

        // Escribir el cuerpo de vuelta en la respuesta
        responseWrapper.copyBodyToResponse();
    }


    private <T> T parseResponseBody(ContentCachingResponseWrapper responseWrapper, Class<T> responseType) throws IOException {
        // Capturar el cuerpo de la respuesta
        byte[] responseBody = responseWrapper.getContentAsByteArray();
        String responseString = new String(responseBody);

        // Usar una librería como Jackson para deserializar el JSON a la clase especificada
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseString, responseType);
    }
}
