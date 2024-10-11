package com.memoir.global.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.memoir.global.exception.BusinessBaseException;
import com.memoir.global.exception.ErrorProperty;
import com.memoir.global.exception.ErrorResponse;
import com.memoir.global.exception.GlobalErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

public class ExceptionFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    public ExceptionFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (BusinessBaseException e) {
            e.printStackTrace();
            errorToJson(e.getErrorCode(), response);
        } catch (Exception e) {
            e.printStackTrace();

            if (e.getCause() instanceof BusinessBaseException) {
                errorToJson(((BusinessBaseException) e.getCause()).getErrorCode(), response);
            } else {
                errorToJson(GlobalErrorCode.INTERNAL_SERVER_ERROR, response);
            }
        }
    }

    private void errorToJson(ErrorProperty errorProperty, HttpServletResponse response ) throws IOException {
        response.setStatus(errorProperty.status());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(ErrorResponse.of(errorProperty)));
        response.flushBuffer();
    }
}
