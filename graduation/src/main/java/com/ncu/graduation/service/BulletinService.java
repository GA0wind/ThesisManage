package com.ncu.graduation.service;


import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmBulletinError;
import com.ncu.graduation.mapper.BulletinMapper;
import com.ncu.graduation.model.Bulletin;
import com.ncu.graduation.model.BulletinExample;
import com.ncu.graduation.vo.BulletinVO;
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

    public PaginationDTO list(Integer page, Integer size, String schoolYear){
        PaginationDTO<BulletinVO> paginationDTO = new PaginationDTO<>();

        BulletinExample bulletinExample = new BulletinExample();
        bulletinExample.createCriteria().andSchoolYearEqualTo(schoolYear);

        Integer totalCount = (int) bulletinMapper.countByExample(bulletinExample);

        paginationDTO.setPagination(totalCount, page, size);

        int offset = size * (page - 1);

        bulletinExample.setOrderByClause("gmt_modified desc");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Bulletin> bulletins = bulletinMapper.selectByExampleWithRowbounds(bulletinExample, new RowBounds(offset, size));
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

    public Bulletin getById(Long id) {
        return bulletinMapper.selectByPrimaryKey(id);
    }

    public void createOrUpdate(Bulletin bulletin) {
        if (bulletin.getId() == null) {
            bulletin.setGmtCreate(new Date());
            bulletin.setGmtModified(bulletin.getGmtCreate());
            bulletinMapper.insertSelective(bulletin);
        } else {
            bulletin.setGmtModified(new Date());
            int updated = bulletinMapper.updateByPrimaryKeySelective(bulletin);
            if (updated != 1) {
                throw new CommonException(EmBulletinError.BULLETIN_PUBLISH_FAIL);
            }
        }
    }
}
