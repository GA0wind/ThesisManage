package com.ncu.graduation.vo;

import com.ncu.graduation.bo.ProjectSelectResultBO;
import com.ncu.graduation.model.ForeignLiterature;
import com.ncu.graduation.model.OpenReport;
import com.ncu.graduation.model.ProjectSelectResult;
import com.ncu.graduation.model.TaskBook;
import com.ncu.graduation.model.Thesis;
import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/4/19 14:27
 * @description：课题进度展示
 * @modified By：
 * @version:
 */
@Data
public class ProjectProgressVO {

  private ProjectSelectResultBO selectResult;
  private TaskBook taskBook;
  private OpenReport openReport;
  private ForeignLiterature foreignLiterature;
  private Thesis thesis;
}
