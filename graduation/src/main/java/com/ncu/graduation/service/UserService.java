package com.ncu.graduation.service;

import com.ncu.graduation.dto.LoginDTO;
import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.dto.UserAddDTO;
import com.ncu.graduation.dto.UserSearchDTO;
import com.ncu.graduation.model.College;
import com.ncu.graduation.model.Student;
import com.ncu.graduation.model.Teacher;
import com.ncu.graduation.vo.UserVO;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

  /**
   * 获取学年
   */
  List<String> getSchoolYear();

  /**
   * 登录
   */
  Object login(LoginDTO loginDTO);


  Integer updatePwd(UserVO user, String newPwd);

  ResultDTO addOrUpdateUser(UserAddDTO userAddDTO, UserVO user);

  void addStuByExcel(MultipartFile excel, UserVO user);

  void addTeaByExcel(MultipartFile excel, UserVO user);

  void setTeaGroup(MultipartFile excel, UserVO user);

  void setStuGroup(MultipartFile excel);

  PaginationDTO<Student> stuList(int page, int size, UserVO user,
      UserSearchDTO userSearchDTO);

  PaginationDTO<Teacher> teaList(int page, int size, UserVO user,
      UserSearchDTO userSearchDTO);

  Student getStu(String sno);

  Teacher getTea(String tno);

  List<College> getCollege();

  List<College> getMajor(String parentNo);
}
