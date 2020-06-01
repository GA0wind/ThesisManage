package com.ncu.graduation.service;


import com.ncu.graduation.dto.BulletinDTO;
import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.model.Bulletin;
import com.ncu.graduation.vo.UserVO;

public interface BulletinService {


  /**
   * 获取公告列表
   */
  PaginationDTO list(Integer page, Integer size, String schoolYear);

  /**
   * 获取公告
   */
  Bulletin getById(Long id);

  /**
   * 发布或修改
   */
  void createOrUpdate(BulletinDTO bulletinDTO, UserVO userVO);

  /**
   * 删除公告
   */
  void delete(Long id);
}
