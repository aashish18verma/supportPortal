package com.sv.supportPortal.constant;

public class SecurityConstant {
    public static final long EXPIREATION_TIME = 432_000_000; //5 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String SV_GROUP = "SV Group";
    public static final String SV_GROUP_ADMINISTRATION = "User management Portal";
    public static final String AUTHORITIES = "authorities";
   public static final String FORBIDDEN_MESSAGE = "you need to login this page";
   public static final String ACCESS_DENIED_MESSAGE = "you donot have permission to access this page";
   public static final String OPTION_HTTP_METHOD ="OPTIONS";
   public static final String[] PUBLIC_URLS = {"/user/login","/user/register","/user/image/**"};
  // public static final String[] PUBLIC_URLS = {"**"};



}
