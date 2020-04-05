package com.ncu.graduation.mapper;

import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ProjectApplyExtMapper {

  List<ProjectApply> getSelectiveProjectByStu(@Param("reg") String reg,
      @Param("schoolYear") String schoolYear);

  List<ProjectApply> getSelectiveProjectByTea(@Param("reg") String reg,
      @Param("schoolYear") String schoolYear);

}