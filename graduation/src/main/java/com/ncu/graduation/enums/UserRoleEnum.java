package com.ncu.graduation.enums;

public enum UserRoleEnum {
    /**
     *
     */
    ADMIN("admin"),
    STUDENT("student"),
    TEACHER("teacher"),
    DIRECTOR("director"),
    ;

    private String role;

    UserRoleEnum(String role){
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public static boolean isRole(String role){
        for (UserRoleEnum value : UserRoleEnum.values()) {
            if (role.equals(value.getRole())){
                return true;
            }
        }
        return false;
    }

}
