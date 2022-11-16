package org.zerock.jdbcex.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/todo/*"})
@Log4j2
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("Login check Filter....");

        //(HttpServletRequest , HttpServletResponse )는 HttpServlet 의 자식 Method를 이용하기 위해서 다운 캐스팅을 진행하였다

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        if(session.getAttribute("loginInfo") == null) {

            resp.sendRedirect("/login");

            return;
        }


        chain.doFilter(request, response);
    }
}
