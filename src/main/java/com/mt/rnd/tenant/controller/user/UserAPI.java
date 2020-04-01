package com.mt.rnd.tenant.controller.user;

import com.mt.rnd.tenant.enums.ResponseEnum;
import com.mt.rnd.tenant.model.User;
import com.mt.rnd.tenant.service.user.UserService;
import com.mt.rnd.tenant.wrapper.GenericAPIResponse;
import com.mt.rnd.util.AppConstants;
import com.mt.rnd.util.MTCoreUtil;
import com.mt.rnd.util.TenantContextHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/user")
public class UserAPI {

    private final Logger logger = LogManager.getLogger(UserAPI.class);
    private ResourceBundle messageBundle = ResourceBundle.getBundle("messages");

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsers(HttpServletRequest request) {
        String tenantId = request.getHeader(AppConstants.TENANT_ID_HEADER);

        logger.info("Get All User API initiated...");
        GenericAPIResponse response = new GenericAPIResponse();
        response.setResponseMessage(messageBundle.getString("user.all.found.error"));
        response.setResponseCode(ResponseEnum.NOT_FOUND.getValue());
        response.setResponseStatus(ResponseEnum.ERROR.getValue());
        response.setResponseData(null);

        try {
            if (MTCoreUtil.isNull(tenantId)) {
                response.setResponseMessage(messageBundle.getString("insufficient.parameter"));
                response.setResponseCode(ResponseEnum.INSUFFICIENT_PARAMETERS.getValue());
                response.setResponseData(null);

                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            TenantContextHolder.setTenantId(tenantId);

            // get requested user
            List<User> users = userService.findAll();
            if (!MTCoreUtil.isListEmpty(users)) {
                response.setResponseMessage(messageBundle.getString("user.all.found.success"));
                response.setResponseCode(ResponseEnum.USR_ALL_FOUND_SUCCESS.getValue());
                response.setResponseStatus(ResponseEnum.SUCCESS.getValue());
                response.setResponseData(users);
                logger.info("Users found successfully..");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception ex) {
            logger.error("getAllUsers failed.", ex.fillInStackTrace());
            response.setResponseStatus(ResponseEnum.ERROR.getValue());
            response.setResponseCode(ResponseEnum.EXCEPTION.getValue());
            response.setResponseMessage(messageBundle.getString("exception.occurs"));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

/*
 * @author    : Irfan Nasim
 * @Date      : 16-Apr-18
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
 * @Project   : HIS
 * @Package   : com.sd.his.controller
 * @FileName  : UserAuthAPI
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
