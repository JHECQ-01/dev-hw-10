package com.example;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TimeZone;

@WebFilter("/time")
public class TimezoneValidateFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String timezone = request.getParameter("timezone");

        if (timezone != null && !timezone.isEmpty()) {
            String[] availableIDs = TimeZone.getAvailableIDs();
            boolean isValid = false;
            for (String id : availableIDs) {
                if (id.equals(timezone)) {
                    isValid = true;
                    break;
                }
            }
            if (!isValid) {
                response.setContentType("text/html");
                response.getWriter().write("<html><body><h1>Invalid timezone</h1></body></html>");
                response.getWriter().flush();
                ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
