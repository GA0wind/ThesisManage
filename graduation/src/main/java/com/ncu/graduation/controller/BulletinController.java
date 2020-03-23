package com.ncu.graduation.controller;

import com.ncu.graduation.dto.BulletinDTO;
import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.model.Bulletin;
import com.ncu.graduation.service.BulletinService;
import com.ncu.graduation.util.FileSave;
import com.ncu.graduation.vo.UserVO;
import java.io.File;
import javax.servlet.http.HttpServletResponse;
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
    model.addAttribute("bulletin", bulletin);
    return "bulletin";
  }



  @GetMapping("/bulletin/publish")
  public String publish() {
    return "bulletin-publish";
  }


  @PostMapping("/bulletin/publish")
  public String doPublish(@Valid BulletinDTO bulletinDTO,
      HttpServletRequest request) {
    UserVO userVO = (UserVO) request.getSession().getAttribute("user");
    bulletinService.createOrUpdate(bulletinDTO,userVO);
    return "redirect:/bulletinView";
  }


  @GetMapping("/bulletin/modify/{id}")
  public String modified(@PathVariable(name = "id") Long id,
      Model model) {
    //数据回显
    Bulletin bulletin = bulletinService.getById(id);
    model.addAttribute("id", bulletin.getId());
    model.addAttribute("title", bulletin.getTitle());
    model.addAttribute("description", bulletin.getDescription());
    model.addAttribute("filePath", bulletin.getFilePath());
    return "bulletin-publish";
  }

  @GetMapping("/bulletin/delete/{id}")
  public String delete(@PathVariable(name = "id") Long id) {
    bulletinService.delete(id);
    return "bulletinView";
  }
}
