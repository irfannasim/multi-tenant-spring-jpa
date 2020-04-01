package com.mt.rnd.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
 * @author    : Irfan Nasim
 * @Date      : 31-Jan-2020
 * @version   : ver. 1.0.0
 *
 * ________________________________________________________________________________________________
 *
 *  Developer				Date		     Version		Operation		Description
 * ________________________________________________________________________________________________
 *
 *
 * ________________________________________________________________________________________________
 *
 * @Project   : bookkeeping-be
 * @Package   : com.sd.bk.configuration
 * @FileName  : WebMvcConfiguration
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
@Component
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor);
    }
}
