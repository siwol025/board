package com.siwol.board.user.config;

import com.siwol.board.user.constant.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(SessionConst.LOGIN_USER) == null) {
            response.sendRedirect("/accounts/login?redirectURL=" + normalizeRequestUri(request, requestURI));
            return false;
        }

        return true;
    }

    private String normalizeRequestUri(HttpServletRequest request, String requestURI) {
        if ("POST".equalsIgnoreCase(request.getMethod()) && requestURI.startsWith("/boards/")) {
            String[] uriParts = requestURI.split("/");
            if (uriParts.length >= 3) {
                String postId = uriParts[2]; // postId 추출
                requestURI = "/boards/" + postId;
            }
        }
        return requestURI;
    }
}
