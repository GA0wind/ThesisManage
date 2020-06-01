package com.ncu.graduation.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/5/25 0:43
 * @description：课题审核提交数据
 * @modified By：
 * @version: 0.0.1
 */
@Data
public class ProjectVerifyDTO {

    @NotBlank(message = "课题编号不能为空")
    private String pno;
    @NotNull(message = "必须选择通过与否")
    private Byte isPass;

  @Max(value = 100,message = "分数不能大于100")
  @Min(value = 0,message = "分数不能小于0")
  private String blindScore;
  private String blindComment;

    @Max(value = 100,message = "分数不能大于100")
    @Min(value = 0,message = "分数不能小于0")
    private String directorScore;
    private String directorComment;


  public Byte getBlindScore() {
    return Byte.parseByte(blindScore);  }

  public Byte getDirectorScore() {
    return Byte.parseByte(directorScore);  }


}
