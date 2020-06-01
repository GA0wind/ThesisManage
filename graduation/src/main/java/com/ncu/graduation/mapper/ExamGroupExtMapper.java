package com.ncu.graduation.mapper;

import com.ncu.graduation.bo.OralExamStuProjectBO;
import com.ncu.graduation.dto.OralExamSearchDTO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamGroupExtMapper {

  public List<OralExamStuProjectBO> getGroupStuProject(@Param("oralExamSearchDTO") OralExamSearchDTO oralExamSearchDTO,
      @Param("schoolYear")String schoolYear, @Param("groupNum")Integer groupNum,@Param("college") String college);
}