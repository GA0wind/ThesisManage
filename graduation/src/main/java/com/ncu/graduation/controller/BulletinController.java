package com.ncu.graduation.controller;

import com.ncu.graduation.dto.BulletinDTO;
import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.model.Bulletin;
import com.ncu.graduation.service.BulletinService;
import com.ncu.graduation.util.FileSave;
import com.ncu.graduation.vo.UserVO;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BulletinController {


  @Autowired
  private BulletinService bulletinService;

  @RequestMapping("/bulletin")
  public String bulletin(HttpServletRequest request,
      Model model,
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "size", defaultValue = "5") Integer size) {
    UserVO userVO = (UserVO) request.getSession().getAttribute("user");
    PaginationDTO paginationDTO = bulletinService.list(page, size, userVO.getSchoolYear());
    model.addAttribute("pagination", paginationDTO);
    return "bulletinView";
  }

  @RequestMapping("/bulletin/{id}")
  public String bulletin(@PathVariable("id") String id,
      Model model) {
    Bulletin bulletin = bulletinService.getById(Long.parseLong(id));
    String[] strings = bulletin.getFilePath().split("/");
    bulletin.setFilePath(strings[strings.length - 1]);
    model.addAttribute("bulletin", bulletin);
    return "bulletin";
  }

  @GetMapping("/bulletin/publish/{id}")
  public String modified(@PathVariable(name = "id") Long id,
      Model model) {
    Bulletin bulletin = bulletinService.getById(id);
    model.addAttribute("title", bulletin.getTitle());
    model.addAttribute("description", bulletin.getDescription());
    model.addAttribute("fileUrl", bulletin.getFilePath());
    return "bulletin-publish";
  }

  @GetMapping("/bulletin/publish")
  public String publish() {
    return "bulletin-publish";
  }

  @PostMapping("/bulletin/publish")
  public String doPublish(@Valid BulletinDTO bulletinDTO,
      HttpServletRequest request) {
//        文件传输
    String fileUrl = null;
    if (bulletinDTO.getFile() != null && !bulletinDTO.getFile().isEmpty()) {
      fileUrl = FileSave.fileSave(bulletinDTO.getFile(), FileTypeEnum.BULLETIN);
    }

    UserVO userVO = (UserVO) request.getSession().getAttribute("user");
    Bulletin bulletin = new Bulletin();
    if (bulletinDTO.getId() != null) {
      bulletin.setId(Long.parseLong(bulletinDTO.getId()));
    }
    bulletin.setTitle(bulletinDTO.getTitle());
    bulletin.setDescription(bulletinDTO.getDesc());
    bulletin.setFilePath(fileUrl);
    bulletin.setSchoolYear(userVO.getSchoolYear());
    bulletin.setCreatorNo(userVO.getAccountNo());
    bulletinService.createOrUpdate(bulletin);
    return "redirect:/bulletinView";
  }
}
