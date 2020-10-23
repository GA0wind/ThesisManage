package com.ncu.graduation.mapper;

import com.ncu.graduation.bo.ProjectSelectResultBO;
import com.ncu.graduation.model.TaskBook;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：grh
 * @date ：Created in 2020/5/13 0:07
 * @description：
 * @modified By：
 * @version:
 */
public interface DocumentExtMapper {

  List<String> getUndoneTaskBook(@Param("schoolYear") String schoolYear);
  List<String> getUndoneForeignLiterature(@Param("schoolYear") String schoolYear);
  List<String> getUndoneOpenReport(@Param("schoolYear") String schoolYear);
  List<String> getUndoneThesis(@Param("schoolYear") String schoolYear);

}
