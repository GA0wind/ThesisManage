package com.ncu.graduation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：grh
 * @date ：Created in 2020/3/21 22:25
 * @description：申请课题类
 * @modified By：
 * @version: 0.0.1
 */
@Data
public class ProjectApplyDTO {
  private String id;
  @NotBlank(message = "标题不能为空")
  private String name;
  @NotBlank(message = "内容能为空")
  private String desc;

  private String type;
  @NotNull(message = "申请文件不能为空")
  private MultipartFile file;
}
