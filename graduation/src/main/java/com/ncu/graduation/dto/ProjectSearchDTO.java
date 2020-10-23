package com.ncu.graduation.dto;

import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/4/15 23:05
 * @description：课题搜索DTO
 * @modified By：
 * @version: 0.0.1
 */
@Data
public class ProjectSearchDTO {
  private String pname;
  private String creatorName;
  private String type;
  private String tags;
}
