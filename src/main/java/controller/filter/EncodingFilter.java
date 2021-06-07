package controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "encoding", value = "utf-8", description = "Encoding Param")})

public class EncodingFilter implements Filter {

    private String code;

    @Override
    public void init(FilterConfig filterConfig)  {
        code = filterConfig.getInitParameter("encoding");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String codeRequest = request.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);

            response.setCharacterEncoding(code);
            filterChain.doFilter(request, response);
        }
    }
    @Override
    public void destroy() {
        code = null;
    }
}
