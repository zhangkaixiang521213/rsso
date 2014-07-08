package com.chinasofti.oauth2.client;

import org.apache.shiro.authc.AuthenticationException;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-18
 * <p>Version: 1.0
 */
public class OAuth2Exception extends AuthenticationException {

    public OAuth2Exception(Throwable cause) {
        super(cause);
    }
}
