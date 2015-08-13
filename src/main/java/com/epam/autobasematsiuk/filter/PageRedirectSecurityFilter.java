package com.epam.autobasematsiuk.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class PageRedirectSecurityFilter redirects the call to the main page with
 * direct reference to jsp
 */
@WebFilter(urlPatterns = {"/jsp/*"}, initParams = {
        @WebInitParam(name = "INDEX_PATH", value = "/index.jsp")})

public class PageRedirectSecurityFilter implements Filter {

    private String indexPath;

    /**
     * The method init from javax.servlet.Filter
     *
     * @param filterConfig is the FilterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        indexPath = filterConfig.getInitParameter("INDEX_PATH");
    }

    /**
     * The method doFilter from javax.servlet.Filter
     *
     * @param servletRequest  is the ServletRequest
     * @param servletResponse is the ServletResponse
     * @param filterChain     is the FilterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + indexPath);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /**
     * The method destroy from javax.servlet.Filter
     */
    @Override
    public void destroy() {

    }
}
