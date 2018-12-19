package com.fifa.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fifa.service.CommonService;
import com.fifa.util.Output;

@Component
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
	
	@Autowired
	private CommonService commonService;

	@Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
      throws IOException, ServletException {
        response.addHeader("WWW-Authenticate", "Basic realm=" +getRealmName());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        
        PrintWriter writer = response.getWriter();

        String trivia = commonService.getTrivia();
        
        Output out = new Output();
        out.setMessage(authEx.getMessage());
        out.setData("trivia", trivia);
        
        writer.println(new ObjectMapper().writeValueAsString(out));
    }
	
	@Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("DeveloperStack");
        super.afterPropertiesSet();
    }

}