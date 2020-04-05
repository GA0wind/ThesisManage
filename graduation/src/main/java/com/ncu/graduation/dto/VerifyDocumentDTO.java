package com.ncu.graduation.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
  private String dno;
  private Byte isBlind = (byte)0;
  private Byte isPass;
  @Max(value = 100,message = "分数不能大于100")
  @Min(value = 0,message = "分数不能小于0")
  private Byte score;
  //审核时的文件, 存下来记录
  private String filePath;
  private String translationFile;
  private String comment;
}
