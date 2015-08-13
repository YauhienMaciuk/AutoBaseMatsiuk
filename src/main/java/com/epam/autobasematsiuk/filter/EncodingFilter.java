package com.epam.autobasematsiuk.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * The Class EncodingFilter changes the encoding for the request and response
 * to UTF-8.
 */
@WebFilter(urlPatterns = {"/*"}, initParams = {
        @WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param")})

public class EncodingFilter implements Filter {

    private String code;

    /**
     * The method init from javax.servlet.Filter
     *
     * @param filterConfig is the FilterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter("encoding");

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
        String codeRequest = servletRequest.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            servletRequest.setCharacterEncoding(code);
            servletResponse.setCharacterEncoding(code);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * The method destroy from javax.servlet.Filter
     */
    @Override
    public void destroy() {
        code = null;
    }
}
