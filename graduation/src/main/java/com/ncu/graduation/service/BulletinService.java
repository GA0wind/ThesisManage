package com.ncu.graduation.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.ncu.graduation.dto.BulletinDTO;
import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmBulletinError;
import com.ncu.graduation.mapper.BulletinMapper;
import com.ncu.graduation.model.Bulletin;
import com.ncu.graduation.model.BulletinExample;
import com.ncu.graduation.util.FileSave;
import com.ncu.graduation.vo.BulletinVO;
import com.ncu.graduation.vo.UserVO;
import java.io.File;
import java.text.SimpleDateFormat;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BulletinService {

    @Autowired
    private BulletinMapper bulletinMapper;

    /**
     * 获取公告列表
     * @param page
     * @param size
     * @param schoolYear
     * @return
     */
    public PaginationDTO list(Integer page, Integer size, String schoolYear){
        PaginationDTO<BulletinVO> paginationDTO = new PaginationDTO<>();

        //获取公告总数
        BulletinExample bulletinExample = new BulletinExample();
        bulletinExample.createCriteria().andSchoolYearEqualTo(schoolYear);
        //获取公告并将公告排序
        bulletinExample.setOrderByClause("gmt_modified desc");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        PageMethod.startPage(page,size);
        List<Bulletin> bulletins = bulletinMapper.selectByExample(bulletinExample);
        PageInfo<Bulletin> pageInfo = new PageInfo<>(bulletins);
        paginationDTO.setPagination((int)pageInfo.getTotal(),page,size);
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
     * @param id
     * @return
     */
    public Bulletin getById(Long id) {
        return bulletinMapper.selectByPrimaryKey(id);
    }

    /**
     * 发布或修改
     * @param bulletinDTO
     * @param userVO
     */
    public void createOrUpdate(BulletinDTO bulletinDTO, UserVO userVO) {

        //判断是修改还是删除
        if (bulletinDTO.getId() != null) {

            //判断是否有新文件
            if (bulletinDTO.getFile() == null || bulletinDTO.getFile().isEmpty()){
                bulletinDTO.setFile(null);
            } else{
                String oldFilePath = FileTypeEnum.BULLETIN.getPreUrl()+bulletinDTO.getOldFilePath();
                File file = new File(oldFilePath);
                //删除旧文件
                file.delete();
            }
        }

//        保存文件
        String fileUrl = null;
        if (bulletinDTO.getFile() != null && !bulletinDTO.getFile().isEmpty()) {
            fileUrl = FileSave.fileSave(bulletinDTO.getFile(), FileTypeEnum.BULLETIN);
        }

        //构建持久层bulletin
        Bulletin bulletin = new Bulletin();
        bulletin.setTitle(bulletinDTO.getTitle());
        bulletin.setDescription(bulletinDTO.getDesc());
        bulletin.setFilePath(fileUrl);
        bulletin.setSchoolYear(userVO.getSchoolYear());
        bulletin.setCreatorNo(userVO.getAccountNo());
        if (bulletinDTO.getId() == null) {
            bulletin.setGmtCreate(new Date());
            bulletin.setGmtModified(bulletin.getGmtCreate());
            bulletinMapper.insertSelective(bulletin);
        } else {
            bulletin.setId(Long.parseLong(bulletinDTO.getId()));
            bulletin.setGmtModified(new Date());
            int updated = bulletinMapper.updateByPrimaryKeySelective(bulletin);
            if (updated != 1) {
                throw new CommonException(EmBulletinError.BULLETIN_PUBLISH_FAIL);
            }
        }
    }

    /**
     * 删除公告
     * @param id
     */
  public void delete(Long id) {
        bulletinMapper.deleteByPrimaryKey(id);
  }
}
