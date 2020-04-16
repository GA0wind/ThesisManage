package com.ncu.graduation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：grh
 * @date ：Created in 2020/4/12 21:34
 * @description：管理员添加可选课题
 * @modified By：
 * @version: 0.0.1
 */

@Data
public class SelectiveProjectDTO {

  @NotBlank(message = "标题不能为空")
  private String pname;
  @NotBlank(message = "内容不能为空")
  private String content;
  private String type;
  @NotBlank(message = "创建者账号不能为空")
  private String creatorNo;
  @NotBlank(message = "创建者姓名不能为空")
  private String creatorName;
  @NotBlank(message = "学院不能为空")
  private String college;
  @NotNull(message = "申请文件不能为空")
  private MultipartFile file;
  private String tags;
  private Byte score;
  private String comment;
}
