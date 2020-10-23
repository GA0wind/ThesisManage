package com.ncu.graduation.mapper;

import com.ncu.graduation.bo.MyProjectSelectStateBO;
import com.ncu.graduation.bo.MySelectStateBO;
import com.ncu.graduation.model.ProjectSelect;
import com.ncu.graduation.model.ProjectSelectExample;
import com.ncu.graduation.vo.ProAndStuNumVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ProjectSelectExtMapper {

  List<MySelectStateBO> getMySelectStateByStu(@Param("selectorNo")String selectNo,@Param("schoolYear") String schoolYear);

  List<MySelectStateBO> getMySelectStateByTea(@Param("selectorNo")String selectNo,@Param("schoolYear") String schoolYear);

  List<MyProjectSelectStateBO> getMyProjectSelectStateByTea(@Param("pno")String pno);

  List<MyProjectSelectStateBO> getMyProjectSelectStateByStu(@Param("pno")String pno);



}