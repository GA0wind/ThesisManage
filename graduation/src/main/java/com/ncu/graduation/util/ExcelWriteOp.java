package com.ncu.graduation.util;

import com.ncu.graduation.vo.OralExamScoreVO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * @author ：grh
 * @date ：Created in 2020/5/2 16:37
 * @description：写入excel
 * @modified By：
 * @version: 0.0.1
 */
public class ExcelWriteOp {

  private static List<String> CELL_HEADS; //列头

  static{
    // 类装载时就载入指定好的列头信息，如有需要，可以考虑做成动态生成的列头
    CELL_HEADS = new ArrayList<>();
    CELL_HEADS.add("学号");
    CELL_HEADS.add("学生姓名");
    CELL_HEADS.add("课题编号");
    CELL_HEADS.add("课题名");
    CELL_HEADS.add("指导老师职工号");
    CELL_HEADS.add("指导老师姓名");
    CELL_HEADS.add("答辩平均分");
    CELL_HEADS.add("指导老师论文评分");
    CELL_HEADS.add("盲审论文评分");
    CELL_HEADS.add("最终得分");
  }

  /**
   * 生成Excel并写入数据信息
   * @param stuScores 数据列表
   * @return 写入数据后的工作簿对象
   */
  public static Workbook exportData(List<OralExamScoreVO> stuScores){
    // 生成xlsx的Excel
    Workbook workbook = new SXSSFWorkbook();

    // 如需生成xls的Excel，请使用下面的工作簿对象，注意后续输出时文件后缀名也需更改为xls
    //Workbook workbook = new HSSFWorkbook();

    // 生成Sheet表，写入第一行的列头
    Sheet sheet = buildDataSheet(workbook);
    //构建每行的数据内容
    int rowNum = 1;
    for (Iterator<OralExamScoreVO> it = stuScores.iterator(); it.hasNext(); ) {
      OralExamScoreVO scoreVO = it.next();
      if (scoreVO == null) {
        continue;
      }
      //输出行数据
      Row row = sheet.createRow(rowNum++);
      convertDataToRow(scoreVO, row);
    }
    return workbook;
  }

  /**
   * 生成sheet表，并写入第一行数据（列头）
   * @param workbook 工作簿对象
   * @return 已经写入列头的Sheet
   */
  private static Sheet buildDataSheet(Workbook workbook) {
    Sheet sheet = workbook.createSheet();
    // 设置列头宽度
    for (int i=0; i<CELL_HEADS.size(); i++) {
      sheet.setColumnWidth(i, 4000);
    }
    // 设置默认行高
    sheet.setDefaultRowHeight((short) 400);
    // 构建头单元格样式
    CellStyle cellStyle = buildHeadCellStyle(sheet.getWorkbook());
    // 写入第一行各列的数据
    Row head = sheet.createRow(0);
    for (int i = 0; i < CELL_HEADS.size(); i++) {
      Cell cell = head.createCell(i);
      cell.setCellValue(CELL_HEADS.get(i));
      cell.setCellStyle(cellStyle);
    }
    return sheet;
  }

  /**
   * 设置第一行列头的样式
   * @param workbook 工作簿对象
   * @return 单元格样式对象
   */
  private static CellStyle buildHeadCellStyle(Workbook workbook) {
    CellStyle style = workbook.createCellStyle();
    //对齐方式设置
    style.setAlignment(HorizontalAlignment.CENTER);
    //边框颜色和宽度设置
    style.setBorderBottom(BorderStyle.THIN);
    style.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 下边框
    style.setBorderLeft(BorderStyle.THIN);
    style.setLeftBorderColor(IndexedColors.BLACK.getIndex()); // 左边框
    style.setBorderRight(BorderStyle.THIN);
    style.setRightBorderColor(IndexedColors.BLACK.getIndex()); // 右边框
    style.setBorderTop(BorderStyle.THIN);
    style.setTopBorderColor(IndexedColors.BLACK.getIndex()); // 上边框
    //设置背景颜色
    style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    //粗体字设置
    Font font = workbook.createFont();
    font.setBold(true);
    style.setFont(font);
    return style;
  }

  /**
   * 将数据转换成行
   * @param scoreVO 源数据
   * @param row 行对象
   * @return
   */
  private static void convertDataToRow(OralExamScoreVO scoreVO, Row row){
    int cellNum = 0;
    Cell cell;
    // 学生学号
    cell = row.createCell(cellNum++);
    cell.setCellValue(null == scoreVO.getProjectSelectResult().getSno() ? "" : scoreVO.getProjectSelectResult().getSno());
    // 学生姓名
    cell = row.createCell(cellNum++);
    cell.setCellValue(null == scoreVO.getProjectSelectResult().getSname() ? "" : scoreVO.getProjectSelectResult().getSname());
    // 课题编号
    cell = row.createCell(cellNum++);
    cell.setCellValue(null == scoreVO.getProjectSelectResult().getPno() ? "" : scoreVO.getProjectSelectResult().getPno());
    // 课题名
    cell = row.createCell(cellNum++);
    cell.setCellValue(null == scoreVO.getProjectSelectResult().getPname() ? "" : scoreVO.getProjectSelectResult().getPname());
    // 指导老师工号
    cell = row.createCell(cellNum++);
    cell.setCellValue(null == scoreVO.getProjectSelectResult().getTno() ? "" : scoreVO.getProjectSelectResult().getTno());
    // 指导老师姓名
    cell = row.createCell(cellNum++);
    cell.setCellValue(null == scoreVO.getProjectSelectResult().getTname() ? "" : scoreVO.getProjectSelectResult().getTname());
    // 答辩平均分
    cell = row.createCell(cellNum++);
    cell.setCellValue(null == scoreVO.getAvg().toString() ? "" : scoreVO.getAvg().toString());
    // 指导老师论文评分
    cell = row.createCell(cellNum++);
    cell.setCellValue(null == scoreVO.getScore().toString() ? "" : scoreVO.getScore().toString());
    // 盲审论文评分
    cell = row.createCell(cellNum++);
    cell.setCellValue(null == scoreVO.getBlindScore().toString() ? "" : scoreVO.getBlindScore().toString());
    // 最终得分
    cell = row.createCell(cellNum++);
    cell.setCellValue(null == scoreVO.getResultScore().toString() ? "" : scoreVO.getResultScore().toString());

  }

}
