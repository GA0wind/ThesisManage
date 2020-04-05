package com.ncu.graduation.vo;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

/**
 * @author ：grh
 * @date ：Created in 2020/3/29 21:22
 * @description：教师方面课题相关文档展示, 任务书, 论文, 开题报告, 文献
 * @modified By：
 * @version: 0.0.1
 */
@Data
public class TeaProjectDocumentVO<T> {
  private String pno;
  private String pname;
  private String sno;
  private String sname;
  private T document;
}
