package com.ncu.graduation.vo;

import com.ncu.graduation.bo.ProjectSelectResultBO;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectSelectResult;
import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/3/29 23:08
 * @description：学生相关文档展示
 * @modified By：
 * @version: 0.0.1
 */
@Data
public class StuProjectDocumentVO<T> {
  private ProjectApply project;
  private ProjectSelectResultBO projectSelect;
  private T document;
}
