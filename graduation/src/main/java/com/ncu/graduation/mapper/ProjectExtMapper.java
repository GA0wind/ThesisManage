package com.ncu.graduation.mapper;

import com.ncu.graduation.util.BlindDistribution.ProjectTwotuple;
import java.util.List;

/**
 * @author ：grh
 * @date ：Created in 2020/3/25 18:48
 * @description：自定义mybatis接口
 * @modified By：
 * @version: 0.0.1
 */
public interface ProjectExtMapper {

  List<ProjectTwotuple> getProjectNoAndTno();
}
