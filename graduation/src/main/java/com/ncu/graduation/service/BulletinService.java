package com.ncu.graduation.service;


import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.ncu.graduation.controller.AdminController;
import com.ncu.graduation.dto.BulletinDTO;
import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmBulletinError;
import com.ncu.graduation.error.EmCommonError;
import com.ncu.graduation.mapper.BulletinMapper;
import com.ncu.graduation.model.Bulletin;
import com.ncu.graduation.model.BulletinExample;
import com.ncu.graduation.util.FileSave;
import com.ncu.graduation.vo.BulletinVO;
import com.ncu.graduation.vo.UserVO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BulletinService {

  @Autowired
  private BulletinMapper bulletinMapper;

  private static Logger logger = LoggerFactory.getLogger(BulletinService.class);

  /**
   * 获取公告列表
   */
  public PaginationDTO list(Integer page, Integer size, String schoolYear) {
    PaginationDTO<BulletinVO> paginationDTO = new PaginationDTO<>();
    BulletinExample bulletinExample = new BulletinExample();
    bulletinExample.createCriteria().andSchoolYearEqualTo(schoolYear);
    //获取公告并将公告排序
    bulletinExample.setOrderByClause("gmt_modified desc");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    PageMethod.startPage(page, size);
    List<Bulletin> bulletins = bulletinMapper.selectByExample(bulletinExample);
    PageInfo<Bulletin> pageInfo = new PageInfo<>(bulletins);
    //获取公告总数
    paginationDTO.setPagination((int) pageInfo.getTotal(), page, size);
    //转换为VO
    List<BulletinVO> bulletinVOs = new ArrayList<>();
    for (Bulletin bulletin : bulletins) {
      BulletinVO bulletinVO = new BulletinVO();
      bulletinVO.setId(bulletin.getId());
      bulletinVO.setTitle(bulletin.getTitle());
      bulletinVO.setGmtModified(simpleDateFormat.format(bulletin.getGmtModified()));
      bulletinVOs.add(bulletinVO);
    }
    paginationDTO.setData(bulletinVOs);
    return paginationDTO;
  }

  /**
   * 获取公告
   */
  public Bulletin getById(Long id) {
    return bulletinMapper.selectByPrimaryKey(id);
  }

  /**
   * 发布或修改
   */
  public void createOrUpdate(BulletinDTO bulletinDTO, UserVO userVO) {

    //判断是添加还是修改
    if (!StringUtils.isBlank(bulletinDTO.getId())) {
      //判断是否有新文件
      if (bulletinDTO.getFile() == null || bulletinDTO.getFile().isEmpty()) {
        bulletinDTO.setFile(null);
      } else {
        String oldFilePath = FileTypeEnum.BULLETIN.getPreUrl() + bulletinDTO.getOldFilePath();
        File file = new File(oldFilePath);
        //删除旧文件
        try {
          Files.deleteIfExists(file.toPath());
        } catch (IOException e) {
          e.printStackTrace();
          throw new CommonException(EmCommonError.UNKNOWN_ERROR, "覆盖文件失败, 请重新修改");
        }
      }
    }

//        保存新文件
    String fileUrl = null;
    if (bulletinDTO.getFile() != null && !bulletinDTO.getFile().isEmpty()) {
      fileUrl = FileSave.fileSave(bulletinDTO.getFile(), FileTypeEnum.BULLETIN);
    }

    //构建持久层bulletin
    Bulletin bulletin = new Bulletin();
    bulletin.setTitle(bulletinDTO.getTitle());
    bulletin.setContent(bulletinDTO.getContent());
    bulletin.setFilePath(fileUrl);
    bulletin.setSchoolYear(userVO.getSchoolYear());
    bulletin.setCreatorNo(userVO.getAccountNo());
    //id为空是新增, id有数字是修改
    if (StringUtils.isBlank(bulletinDTO.getId())) {
      bulletin.setGmtCreate(new Date());
      bulletin.setGmtModified(bulletin.getGmtCreate());
      bulletinMapper.insertSelective(bulletin);
      logger.info("[{}]发布了公告, 公告id=[{}]", userVO.getAccountNo(), bulletin.getId());
    } else {
      bulletin.setId(Long.parseLong(bulletinDTO.getId()));
      bulletin.setGmtModified(new Date());

      if (bulletinMapper.updateByPrimaryKeySelective(bulletin) != 1) {
        throw new CommonException(EmBulletinError.BULLETIN_PUBLISH_FAIL, "修改失败, 请检查参数是否正确");
      }
      logger.info("[{}]修改了公告id=[{}]的信息", userVO.getAccountNo(), bulletin.getId());
    }
  }

  /**
   * 删除公告
   */
  public void delete(Long id) {
    bulletinMapper.deleteByPrimaryKey(id);
  }
}
