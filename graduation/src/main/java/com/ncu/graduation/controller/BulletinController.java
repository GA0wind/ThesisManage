package com.ncu.graduation.controller;

import com.ncu.graduation.dto.BulletinDTO;
import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.error.EmCommonError;
import com.ncu.graduation.model.Bulletin;
import com.ncu.graduation.service.impl.BulletinServiceImpl;
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
  private BulletinServiceImpl bulletinService;

  @GetMapping("/bulletin")
  public String bulletin(HttpServletRequest request,
      Model model,
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "size", defaultValue = "10") Integer size) {
    UserVO userVO = (UserVO) request.getSession().getAttribute("user");
    PaginationDTO paginationDTO = bulletinService.list(page, size, userVO.getSchoolYear());
    model.addAttribute("pagination", paginationDTO);
    return "bulletin/bulletinView";
  }

  @GetMapping("/bulletin/{id}")
  public String bulletin(@PathVariable("id") String id,
      Model model) {
    Bulletin bulletin = bulletinService.getById(Long.parseLong(id));
    model.addAttribute("bulletin", bulletin);
    return "bulletin/bulletin";
  }



  @GetMapping("/bulletin/publish")
  public String publish() {
    return "bulletin/bulletin-publish";
  }


  @ResponseBody
  @PostMapping("/bulletin/publish")
  public ResultDTO doPublish(@Valid BulletinDTO bulletinDTO,
      HttpServletRequest request) {
    UserVO userVO = (UserVO) request.getSession().getAttribute("user");
    String fileName = bulletinDTO.getFile().getOriginalFilename();
    String[] fileNameArr = new String[0];
    if (fileName != null) {
      fileNameArr = fileName.split("\\.");
    }
    // 获取文件的后缀名
    String suffix = fileNameArr[fileNameArr.length - 1];

    if (!suffix.equalsIgnoreCase("png") &&
        !suffix.equalsIgnoreCase("jpg") &&
        !suffix.equalsIgnoreCase("jpeg") &&
        !suffix.equalsIgnoreCase("doc") &&
        !suffix.equalsIgnoreCase("docx") &&
        !suffix.equalsIgnoreCase("pdf") &&
        !suffix.equalsIgnoreCase("xls") &&
        !suffix.equalsIgnoreCase("xlsx")) {
      return ResultDTO.errorOf(EmCommonError.PARAMETER_VALIDATION_ERROR.getErrCode(),"文件格式不正确");
    }
    bulletinService.createOrUpdate(bulletinDTO,userVO);
    return ResultDTO.okOf();
  }


  @GetMapping("/bulletin/modify/{id}")
  public String modified(@PathVariable(name = "id") Long id,
      Model model) {
    //数据回显
    Bulletin bulletin = bulletinService.getById(id);
    model.addAttribute("id", bulletin.getId());
    model.addAttribute("title", bulletin.getTitle());
    model.addAttribute("content", bulletin.getContent());
    model.addAttribute("filePath", bulletin.getFilePath());
    return "bulletin/bulletin-publish";
  }

  @GetMapping("/bulletin/delete/{id}")
  public String delete(@PathVariable(name = "id") Long id) {
    bulletinService.delete(id);
    return "bulletin/bulletinView";
  }
}
