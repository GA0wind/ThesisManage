package com.ncu.graduation.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserRoleEnum {
  /**
   *
   */
  ADMIN("admin", "管理员"),
  STUDENT("student", "学生"),
  TEACHER("teacher", "指导老师"),
  DIRECTOR("director", "主任"),
  ;

  private String role;
  private String roleName;

  UserRoleEnum(String role, String roleName) {
    this.role = role;
    this.roleName = roleName;
  }

  public String getRole() {
    return this.role;
  }

  public String getRoleName() {
    return this.roleName;
  }

  public static boolean isRole(String role) {
    for (UserRoleEnum value : UserRoleEnum.values()) {
      if (role.equals(value.getRole())) {
        return true;
      }
    }
    return false;
  }

  public static Map<String, String> RoleMap() {
    Map<String, String> roleMap = new HashMap<>();
    for (UserRoleEnum value : UserRoleEnum.values()) {
      roleMap.put(value.getRole(), value.getRoleName());
    }
    return roleMap;
  }

}
