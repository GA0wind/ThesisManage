package com.ncu.graduation.vo;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/4/1 18:20
 * @description：答辩成绩展示VO
 * @modified By：
 * @version: 0.0.1
 */
@Data
public class OralExamScoreVO
{
  private List<Score> scores;
  private BigDecimal score;
  private BigDecimal blindScore;
  private BigDecimal avg;
  private BigDecimal resultScore;
  @Data
  public static class Score{
    private String tno;
    private String tname;
    private Byte score;
  }
}
