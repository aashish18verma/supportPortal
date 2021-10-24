package com.sv.supportPortal.constant;

public class Authorities {
    public static final String[] USER_AUTHORITES = {"user:read"};
    public static final String[] HR_AUTHORITES = {"user:read","user:update"};
    public static final String[] MANAGER_AUTHORITES = {"user:read","user:update"};
    public static final String[] ADMIN_AUTHORITES = {"user:read","user:update","user:create"};
    public static final String[] SUPER_ADMIN_AUTHORITES = {"user:read","user:update","user:create","user:delete"};
}
