package com.laberit.sina.bootcamp.modulo3.spring_web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.ProductStatistic;
import com.laberit.sina.bootcamp.modulo3.spring_web.repository.ProductStatisticRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class Filter extends OncePerRequestFilter {

    @Autowired
    private ProductStatisticRepository productStatisticRepository;

    private final Map<String, Integer> valueOccurrences = new HashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        // Pasar la petición por la cadena de filtros
        filterChain.doFilter(request, responseWrapper);

        // Parsea el cuerpo de la respuesta a la clase especificada y haz algo con ella
        Map<String, Object> responseBody = parseResponseBody(responseWrapper, Map.class);
        countValues(responseBody);

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

    private void countValues(Map<String, Object> responseMap) {
        responseMap.values().forEach(this::processValue);
    }

    private void processValue(Object value) {
        if (value instanceof Map) {
            countValues((Map<String, Object>) value);
        } else if (value instanceof Iterable) {
            ((Iterable<?>) value).forEach(this::processValue);
        } else {
            incrementValueCount(value.toString());
        }
    }

    private void incrementValueCount(String value) {
        try {
            Long productId = Long.parseLong(value);
            ProductStatistic productStatistic = productStatisticRepository.findById(productId).orElse(new ProductStatistic());
            productStatistic.setId(productId);
            productStatistic.setCount(productStatistic.getCount() + 1);
            productStatisticRepository.save(productStatistic);
        } catch (NumberFormatException e) {
            // El valor no es un número, no hacer nada
        }
    }
}