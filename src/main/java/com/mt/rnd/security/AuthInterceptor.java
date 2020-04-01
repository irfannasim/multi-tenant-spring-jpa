package com.mt.rnd.security;

import com.mt.rnd.tenant.repository.user.UserRepository;
import com.mt.rnd.tenant.model.User;
import com.mt.rnd.util.AppConstants;
import com.mt.rnd.util.TenantContextHolder;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        boolean isPassedToResource;
        String tenantId = httpServletRequest.getHeader(AppConstants.TENANT_ID_HEADER);

        if (tenantId != null) {
            TenantContextHolder.setTenantId(tenantId);
            String[] credentials = this.getCredentials(httpServletRequest.getHeader("Authorization"));

            User user = userRepository.findByUsernameAndTenant(credentials[0], tenantId);
            if (user != null) {
                if (!user.getActive()) {
                    httpServletResponse.setContentType("application/json");
                    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                    isPassedToResource = false;
                } else {
                    if (BCrypt.checkpw(credentials[1], user.getPassword())) {
                        isPassedToResource = true;
                    } else {
                        httpServletResponse.setContentType("application/json");
                        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                        isPassedToResource = false;
                    }
                }
            } else {
                httpServletResponse.setContentType("application/json");
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                isPassedToResource = false;
            }
        } else {
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            isPassedToResource = false;
        }
        return isPassedToResource;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private String[] getCredentials(String encodedCred) {
        String[] credentials;
        encodedCred = encodedCred.replace("Basic ", "");
        String decodedCred = new String(Base64.decodeBase64(encodedCred));
        credentials = decodedCred.split(":");

        return credentials;
    }
}
