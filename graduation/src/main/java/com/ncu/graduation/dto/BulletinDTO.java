package com.ncu.graduation.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：mmzs
 * @date ：Created in 2020/3/19 17:38
 * @description：发布公告接收参数
 * @modified By：
 * @version: 0.0.1
 */
@Data
public class BulletinDTO {
  private String id;
  @NotBlank(message = "标题不能为空")
  private String title;
  @NotBlank(message = "内容能为空")
  private String desc;

  private MultipartFile file;
}
