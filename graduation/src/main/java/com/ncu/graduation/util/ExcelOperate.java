package com.ncu.graduation.util;

import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmCommonError;
import com.ncu.graduation.error.EmUserOperatorError;
import com.ncu.graduation.model.Student;
import com.ncu.graduation.model.Teacher;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author ：grh
 * @date ：Created in 2020/3/26 20:55
 * @description：读取excel文件
 * @modified By：
 * @version: 0.0.1
 */
@Slf4j
@Component
public class ExcelOperate {

  private static Logger logger;
//    Logger.getLogger(ExcelReader.class.getCreatorName()); // 日志打印类

  private static final String XLS = "xls";
  private static final String XLSX = "xlsx";

  /**
   * 根据文件后缀名类型获取对应的工作簿对象
   *
   * @param inputStream 读取文件的输入流
   * @param fileType 文件后缀名类型（xls或xlsx）
   * @return 包含文件数据的工作簿对象
   */
  public static Workbook getWorkbook(InputStream inputStream, String fileType) throws IOException {
    Workbook workbook = null;
    if (fileType.equalsIgnoreCase(XLS)) {
      workbook = new HSSFWorkbook(inputStream);
    } else if (fileType.equalsIgnoreCase(XLSX)) {
      workbook = new XSSFWorkbook(inputStream);
    }
    return workbook;
  }

  /**
   * 读取Excel文件内容
   *
   * @param fileName 要读取的Excel文件所在路径
   * @return 读取结果列表，读取失败时返回null
   */
  public static Map<String,Object> readExcel(String fileName,String resultType) {

    Workbook workbook = null;
    FileInputStream inputStream = null;

    try {
      // 获取Excel后缀名
      String fileType = fileName.substring(fileName.lastIndexOf('.') + 1);
      // 获取Excel文件
      File excelFile = new File(FileTypeEnum.BULLETIN.getPreUrl()+fileName);
      if (!excelFile.exists()) {
        logger.warn("指定的Excel文件不存在！");
        return null;
      }

      // 获取Excel工作簿
      inputStream = new FileInputStream(excelFile);
      workbook = getWorkbook(inputStream, fileType);

      // 读取excel中的数据
      Map<String,Object> resultDataList = parseExcel(workbook,resultType);

      return resultDataList;
    } catch (Exception e) {
      logger.warn("解析Excel失败，文件名：[{}] 错误信息：[{}]", fileName, e.getMessage());
      return null;
    } finally {
      try {
        if (null != workbook) {
          workbook.close();
        }
        if (null != inputStream) {
          inputStream.close();
        }
      } catch (Exception e) {
        logger.warn("关闭数据流出错！错误信息：[{}]", e.getMessage());
        return null;
      }
    }
  }

  /**
   * 解析Excel数据
   *
   * @param workbook Excel工作簿对象
   * @return 解析结果
   */
  private static Map<String,Object> parseExcel(Workbook workbook,String resultType) {
    Map<String,Object> resultDataList = new HashMap<>();
    // 解析sheet
    for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
      Sheet sheet = workbook.getSheetAt(sheetNum);

      // 校验sheet是否合法
      if (sheet == null) {
        continue;
      }

      // 获取第一行数据
      int firstRowNum = sheet.getFirstRowNum();
      Row firstRow = sheet.getRow(firstRowNum);
      if (null == firstRow) {
        logger.warn("解析Excel失败，在第一行没有读取到任何数据！");
      }

      // 解析每一行的数据，构造数据对象
      int rowStart = firstRowNum + 1;
      int rowEnd = sheet.getPhysicalNumberOfRows();
      for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
        Row row = sheet.getRow(rowNum);
        if (null == row) {
          continue;
        }
        Map<String,Object> resultData = null;
        try {
          if ("StuExl".equals(resultType)){
            resultData = convertRowToStu(row);
          }
          if ("TeaExl".equals(resultType) ){
            resultData = convertRowToTea(row);
          }
          if ("LeadNumExl".equals(resultType) ){
            resultData = convertRowToTeaAndLeadNum(row);
          }
          if ("StuGroupExl".equals(resultType) ){
            resultData = convertRowToStuAndGroup(row);
          }
          if ("TeaGroupExl".equals(resultType) ){
            resultData = convertRowToTeaAndGroup(row);
          }
        } catch (CommonException e) {
          logger.warn("第 [{}] 行数据不合法！", row.getRowNum());
          e.setErrMsg("第 " + row.getRowNum() + " 行数据不合法！");
          throw e;
        }
        if (resultData == null){
          throw new CommonException(EmCommonError.UNKNOWN_ERROR);
        }
        resultDataList.putAll(resultData);
      }
    }

    return resultDataList;
  }

  /**
   * 将单元格内容转换为字符串
   */
  private static String convertCellValueToString(Cell cell) {
    if (cell == null) {
      return null;
    }
    String returnValue = null;
    switch (cell.getCellType()) {
      case NUMERIC:   //数字
        Double doubleValue = cell.getNumericCellValue();
        // 格式化科学计数法，取一位整数
        DecimalFormat df = new DecimalFormat("0");
        returnValue = df.format(doubleValue);
        break;
      case STRING:    //字符串
        returnValue = cell.getStringCellValue();
        break;
      case BOOLEAN:   //布尔
        Boolean booleanValue = cell.getBooleanCellValue();
        returnValue = booleanValue.toString();
        break;
      case BLANK:     // 空值
        throw new CommonException(EmCommonError.PARAMETER_VALIDATION_ERROR,"不能为空");
      case FORMULA:   // 公式
        returnValue = cell.getCellFormula();
        break;
      case ERROR:     // 故障
        throw new CommonException(EmCommonError.PARAMETER_VALIDATION_ERROR);
      default:
        break;
    }
    return returnValue;
  }

  /**
   * 提取每一行中需要的数据，构造成为一个结果数据对象
   *
   * 当该行中有单元格的数据为空或不合法时，忽略该行的数据
   *
   * @param row 行数据
   * @return 解析后的行数据对象，行数据错误时返回null
   */
  private static Map<String,Object> convertRowToStu(Row row) {
    Student student = new Student();

    Cell cell;
    int cellNum = 0;
    // 获取学号
    cell = row.getCell(cellNum++);
    String sno = convertCellValueToString(cell);
    student.setSno(sno);
    // 获取密码
    cell = row.getCell(cellNum++);
    String password = convertCellValueToString(cell);
    student.setPassword(password);
    // 获取姓名
    cell = row.getCell(cellNum++);
    String sname = convertCellValueToString(cell);
    student.setSname(sname);
    // 获取学院
    cell = row.getCell(cellNum++);
    String college = convertCellValueToString(cell);
    student.setCollege(college);
    // 获取专业
    cell = row.getCell(cellNum++);
    String major = convertCellValueToString(cell);
    student.setMajor(major);
    //获取班级
    cell = row.getCell(cellNum++);
    String gradeClass = convertCellValueToString(cell);
    student.setGradeClass(gradeClass);
    Map<String, Object> map = new HashMap<>();
    map.put(sno, student);
    return map;
  }

  private static Map<String, Object> convertRowToTea(Row row) {
    Teacher teacher = new Teacher();

    Cell cell;
    int cellNum = 0;
    // 获取学号
    cell = row.getCell(cellNum++);
    String tno = convertCellValueToString(cell);
    teacher.setTno(tno);
    // 获取密码
    cell = row.getCell(cellNum++);
    String password = convertCellValueToString(cell);
    teacher.setPassword(password);
    // 获取姓名
    cell = row.getCell(cellNum++);
    String tname = convertCellValueToString(cell);
    teacher.setTname(tname);
    // 获取学院
    cell = row.getCell(cellNum++);
    String college = convertCellValueToString(cell);
    teacher.setCollege(college);
    // 获取角色
    cell = row.getCell(cellNum++);
    String role = convertCellValueToString(cell);
    teacher.setRole(role);
    // 获取职称
    cell = row.getCell(cellNum++);
    String title = convertCellValueToString(cell);
    teacher.setTitle(title);
    // 指导人数
    cell = row.getCell(cellNum++);
    String studentNum = convertCellValueToString(cell);
    teacher.setLeadStudentNum(Byte.parseByte(studentNum));

    Map<String, Object> map = new HashMap<>();
    map.put(tno, teacher);
    return map;
  }

  private static Map<String,Object> convertRowToTeaAndLeadNum(Row row) {
    Teacher teacher = new Teacher();
    Cell cell;
    int cellNum = 0;
    // 获取工号
    cell = row.getCell(cellNum++);
    String tno = convertCellValueToString(cell);
    teacher.setTno(tno);
    //获取指导人数
    cell = row.getCell(cellNum++);
    Byte leadNum = Byte.parseByte(convertCellValueToString(cell));
    teacher.setLeadStudentNum(leadNum);
    Map<String, Object> map = new HashMap<>();
    map.put(tno, teacher);
    return map;
  }

  private static Map<String,Object> convertRowToTeaAndGroup(Row row) {
    Teacher teacher = new Teacher();
    Cell cell;
    int cellNum = 0;
    // 获取工号
    cell = row.getCell(cellNum++);
    String tno = convertCellValueToString(cell);
    teacher.setTno(tno);
    //获取组号
    cell = row.getCell(cellNum++);
    Integer groupNum = Integer.parseInt(convertCellValueToString(cell));
    teacher.setGroupNum(groupNum);
    //获取组内角色
    cell = row.getCell(cellNum++);
    String role = convertCellValueToString(cell);
    teacher.setGroupRole(role);
    //获取学院
    cell = row.getCell(cellNum++);
    String college = convertCellValueToString(cell);
    teacher.setCollege(college);
    Map<String, Object> map = new HashMap<>();
    map.put(tno, teacher);
    return map;
  }

  private static Map<String,Object> convertRowToStuAndGroup(Row row) {
    Student student = new Student();
    Cell cell;
    int cellNum = 0;
    // 获取学号
    cell = row.getCell(cellNum++);
    String sno = convertCellValueToString(cell);
    student.setSno(sno);
    //获取组号
    cell = row.getCell(cellNum++);
    Integer groupNum = Integer.parseInt(convertCellValueToString(cell));
    student.setGroupNum(groupNum);
    Map<String, Object> map = new HashMap<>();
    map.put(sno, student);
    return map;
  }

}

