package com.ncu.graduation.vo;

import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectSelectResult;
import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/3/21 22:02
 * @description：课题详情信息
 * @modified By：
 * @version: 0.0.1
 */

@Data
public class ProjectInfoVO {
  private ProjectApply projectApply;
  private ProjectSelectResult projectSelectResult;
}
