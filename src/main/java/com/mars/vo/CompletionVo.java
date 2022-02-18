package com.mars.vo;

import com.mars.entity.Completion;
import lombok.Data;

import java.io.Serializable;

@Data
public class CompletionVo implements Serializable {
   private Completion all;
   private Completion male;
   private Completion female;
   private String college;
   private String major;
   private String cls;
   private String teacher;
   private String year;
   private String xueqi;



}
