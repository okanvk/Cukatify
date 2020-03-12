package com.okanciftci.cukatify.security;

public class SecurityConstants {

    public static final String SIGN_UP_URLS = "/users/**";
    public static final String SECRET = "SecretKeyToGenJWT";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 30_000; // for 30 second time
}
