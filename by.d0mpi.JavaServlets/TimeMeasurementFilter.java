package by.d0mpi.JavaServlets;

import javax.servlet.*;
import java.io.IOException;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class TimeMeasurementFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long end = System.currentTimeMillis();

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String path = httpRequest.getRequestURI();
        String method = httpRequest.getMethod();

        System.out.printf("%s - '%s' - done(%d ms) %n",method ,path, end-start);
    }

    @Override
    public void destroy() {

    }
}

