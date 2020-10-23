package com.ncu.graduation.mapper;

import com.ncu.graduation.bo.ProjectSelectResultBO;
import com.ncu.graduation.model.ProjectSelectResult;
import com.ncu.graduation.model.ProjectSelectResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ProjectSelectResultExtMapper {

  List<ProjectSelectResultBO> getProjectSelectResultByTea(@Param("tno") String tno,
      @Param("schoolYear") String schoolYear);

  List<ProjectSelectResultBO> getProjectSelectResultByPnos(@Param("pnos") List<String> pnos);

  List<ProjectSelectResultBO> getProjectSelectResultBySnos(@Param("snos") List<String> snos);

}