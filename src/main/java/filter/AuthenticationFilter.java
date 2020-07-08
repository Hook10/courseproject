package filter;


import entity.UserStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static constants.ParamAndAttributeConstants.USER_STATUS;

public class AuthenticationFilter implements Filter {


    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        UserStatus status = (UserStatus) session.getAttribute(USER_STATUS);

        if (status == null) {
            session.setAttribute(USER_STATUS, UserStatus.GUEST);
        }
        filterChain.doFilter(request, response);
    }


    @Override
    public void destroy() {
    }


}
