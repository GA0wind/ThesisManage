package com.ncu.graduation.mapper;

import com.ncu.graduation.util.BlindDistribution;
import com.ncu.graduation.util.BlindDistribution.TeacherTwotuple;
import java.util.List;

/**
 * @author ：grh
 * @date ：Created in 2020/3/25 18:49
 * @description：自定义TeacherMapper接口
 * @modified By：
 * @version: 0.0.1
 */
public interface TeacherExtMapper {

  List<TeacherTwotuple> selectTnoAndStudentNum();
}
