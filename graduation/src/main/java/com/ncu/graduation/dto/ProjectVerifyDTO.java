package com.ncu.graduation.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/3/24 20:40
 * @description：课题审核DTO模型
 * @modified By：
 * @version: 0.0.1
 */
@Data
public class ProjectVerifyDTO {
  @NotBlank(message = "课题编号不能为空")
  private String pno;
  @Max(value = 100,message = "分数不能大于100")
  @Min(value = 0,message = "分数不能小于0")
  private Integer score;
  private String comment;

}
