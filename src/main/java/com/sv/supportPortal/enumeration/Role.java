package com.sv.supportPortal.enumeration;

import static com.sv.supportPortal.constant.Authorities.*;

public enum Role {
    ROLE_USER(USER_AUTHORITES),
    ROLE_HR(HR_AUTHORITES),
    ROLE_MANAGER(MANAGER_AUTHORITES),
    ROLE_ADMIN(ADMIN_AUTHORITES),
    ROLE_SUPER_ADMIN(SUPER_ADMIN_AUTHORITES);

    private String[] authorites;
    Role(String... authorites){
        this.authorites = authorites;
    }
    public String[] getAuthorites(){
        return authorites;
    }
}
