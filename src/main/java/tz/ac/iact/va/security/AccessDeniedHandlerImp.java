package tz.ac.iact.va.security;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AccessDeniedHandlerImp implements AccessDeniedHandler {

@Override
public void handle(HttpServletRequest request, HttpServletResponse response,
                   AccessDeniedException accessDeniedException) throws IOException, ServletException {


    // Set response code
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);

    // Set response content type to JSON
    response.setContentType("application/json;charset=UTF-8");

    Map<String,Object> obj=new HashMap<>();

    obj.put("success",false);

    obj.put("message","Access Forbidden");


    // Add content to the response
//    response.getWriter().write(new Gson().toJson(obj));
    response.getWriter().write(new Gson().toJson(obj));

    }
}