package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static constants.ParamAndAttributeConstants.THE_LANGUAGE_LOCAL;

public class SessionLocaleFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getParameter(THE_LANGUAGE_LOCAL) != null) {
            req.getSession().setAttribute(THE_LANGUAGE_LOCAL, req.getParameter(THE_LANGUAGE_LOCAL));
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }

    public void init(FilterConfig arg0) throws ServletException {
    }
}
