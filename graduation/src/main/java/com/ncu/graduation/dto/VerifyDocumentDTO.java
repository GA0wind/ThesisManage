package com.ncu.graduation.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/3/28 23:28
 * @description：老师审核毕设相关文档模型
 * @modified By：
 * @version: 0.0.1
 */

@Data
public class VerifyDocumentDTO {
  @NotBlank(message = "课题编号不能为空")
  private String pno;
  private Byte isBlind = (byte)0;
  private Byte isPass;
  @Max(value = 100,message = "分数不能大于100")
  @Min(value = 0,message = "分数不能小于0")
  private String score;
  private String comment;

  public Byte getScore() {
    return Byte.parseByte(score);
  }
}
