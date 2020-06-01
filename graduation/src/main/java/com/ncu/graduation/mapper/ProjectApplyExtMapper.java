package com.ncu.graduation.mapper;

import com.ncu.graduation.bo.SelectiveProjectBO;
import com.ncu.graduation.dto.ProjectSearchDTO;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectApplyExample;
import com.ncu.graduation.util.BlindDistribution.ProjectTwotuple;
import com.ncu.graduation.vo.ProAndStuNumVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ProjectApplyExtMapper {

  List<SelectiveProjectBO> getSelectiveProjectByStu(@Param("reg") String reg,
      @Param("schoolYear") String schoolYear, @Param("college") String college,
      @Param("projectSearchDTO") ProjectSearchDTO projectSearchDTO);

  List<SelectiveProjectBO> getSelectiveProjectByTea(@Param("reg") String reg,
      @Param("schoolYear") String schoolYear, @Param("college") String college,
      @Param("projectSearchDTO") ProjectSearchDTO projectSearchDTO);

  List<ProjectTwotuple> getSelectedProjectNoAndTno(@Param("schoolYear") String schoolYear,
      @Param("college") String college);

  List<ProjectTwotuple> getProjectApplyNoAndTno(@Param("schoolYear") String schoolYear,
      @Param("college") String college);

  List<ProAndStuNumVO> countByCollege(@Param("schoolYear") String schoolYear);
}