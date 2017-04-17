/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ralloc.controller;

import com.ralloc.bean.RoomBean;
import com.ralloc.bean.SubjectStudentUsn;
import com.ralloc.model.DepartmentSubject;
import com.ralloc.model.Department;
import com.ralloc.model.Subject;
import java.util.HashMap;
import java.util.ArrayList;
import java.sql.SQLException;
import java.math.BigInteger;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHeightRule;
/**
 *
 * @author saurabh
 */
public class BFormDocument {

   HashMap<RoomBean, ArrayList<SubjectStudentUsn>> detailedRoomMap;
   String date, time;
   XWPFDocument document;

   public BFormDocument(HashMap<RoomBean, ArrayList<SubjectStudentUsn>> detailRoomMap, String formDate, String formTime) {
      detailedRoomMap = detailRoomMap;
      date = formDate;
      time = formTime;
      document = new XWPFDocument();
   }

   private void writeRun(XWPFParagraph para, String text, boolean breakLine) {
      XWPFRun run = para.createRun();
      run.setText(text);
      run.setFontFamily("Calibri");
      run.setFontSize(10);
      if (breakLine) run.addBreak();
   }

   private void writeRun(XWPFParagraph para, String text, boolean breakLine, int fontSize) {
      XWPFRun run = para.createRun();
      run.setText(text);
      run.setFontFamily("Calibri");
      run.setFontSize(fontSize);
      if (breakLine) run.addBreak();
   }

   private void writeHeading() {
      XWPFParagraph heading = document.createParagraph();
      heading.setPageBreak(true);
      heading.setAlignment(ParagraphAlignment.CENTER);
      writeRun(heading, "B.M.S COLLEGE OF ENGINEERING(Autonomous Institution under VTU), BANGALORE - 560 019", true);
      writeRun(heading, "Attendance and Room Superintendent's Report", true);
      writeRun(heading, "B.E./B.Arch./M.B.A/M.C.A/M.Tech./Ph.D./M.Sc.(Res) _______ Semester Examination " + date.substring(3, 10), true);
   }

   private void writeCourseDetails(SubjectStudentUsn subject) {
      String courseCode = subject.getCourseCode();
      //create table
      XWPFTable table = document.createTable(2, 2);

      XWPFParagraph departmentDate = table.getRow(0).getCell(0).getParagraphArray(0);
      departmentDate.setPageBreak(false);
      departmentDate.setAlignment(ParagraphAlignment.LEFT);
      try {
         String dept = "Department: " + Department.getDepartmentNameById(DepartmentSubject.getDepartmentIdByCourseCode(courseCode));
         writeRun(departmentDate, dept + "          Date: " + date, false);
      } catch (SQLException e) {
         System.out.println(e);
      }

      XWPFParagraph timePara = table.getRow(0).getCell(1).getParagraphArray(0);
      timePara.setPageBreak(false);
      timePara.setAlignment(ParagraphAlignment.RIGHT);
      writeRun(timePara, "Time: " + time + " to ________", true);

      XWPFParagraph courseName = table.getRow(1).getCell(0).getParagraphArray(0);
      courseName.setPageBreak(false);
      courseName.setAlignment(ParagraphAlignment.LEFT);
      try {
         writeRun(courseName, "Subject: " + Subject.getNameByCourseCode(courseCode), false);
      } catch (SQLException e) {
         System.out.println(e);
      }

      XWPFParagraph code = table.getRow(1).getCell(1).getParagraphArray(0);
      code.setPageBreak(false);
      code.setAlignment(ParagraphAlignment.RIGHT);
      writeRun(code, "Course Code: " + courseCode, true);

      table.setInsideHBorder(XWPFTable.XWPFBorderType.SINGLE, 1, 1, "FFFFFF");
      table.setInsideVBorder(XWPFTable.XWPFBorderType.SINGLE, 1, 1, "FFFFFF");
      table.getRow(0).setHeight(300);
      table.getRow(0).getCtRow().getTrPr().getTrHeightArray(0).setHRule(STHeightRule.EXACT);
      table.getRow(1).setHeight(300);
      table.getRow(1).getCtRow().getTrPr().getTrHeightArray(0).setHRule(STHeightRule.EXACT);

      CTTbl ctable = table.getCTTbl();
      CTTblPr pr = ctable.getTblPr();

      CTTblWidth tblW = pr.getTblW();
      tblW.setW(BigInteger.valueOf(5000));
      tblW.setType(STTblWidth.PCT);
      pr.setTblW(tblW);

      CTTblBorders tblB = pr.getTblBorders();
      CTBorder border = tblB.addNewBottom();
      border.setColor("FFFFFF");
      tblB.setBottom(border);
      tblB.setTop(border);
      tblB.setRight(border);
      tblB.setLeft(border);

      ctable.setTblPr(pr);
   }

   private void writeUSN(SubjectStudentUsn subject) {
      XWPFTable table = document.createTable(1, 5);

      XWPFParagraph usnColumn = table.getRow(0).getCell(0).getParagraphArray(0);
      usnColumn.setAlignment(ParagraphAlignment.CENTER);
      writeRun(usnColumn, "USN", false, 7);
      XWPFParagraph bookletNo = table.getRow(0).getCell(1).getParagraphArray(0);
      bookletNo.setAlignment(ParagraphAlignment.CENTER);
      writeRun(bookletNo, "Booklet/Drg. Sheet No.", false, 7);
      XWPFParagraph signature = table.getRow(0).getCell(2).getParagraphArray(0);
      signature.setAlignment(ParagraphAlignment.CENTER);
      writeRun(signature, "Signature", false, 7);
      XWPFParagraph additionalBooklet = table.getRow(0).getCell(3).getParagraphArray(0);
      additionalBooklet.setAlignment(ParagraphAlignment.CENTER);
      writeRun(additionalBooklet, "Addl. Booklet/Drg./Graph Sheet No.", false, 7);
      XWPFParagraph total = table.getRow(0).getCell(4).getParagraphArray(0);
      total.setAlignment(ParagraphAlignment.CENTER);
      writeRun(total, "Total", false, 7);

      table.getRow(0).setRepeatHeader(true);
      table.getRow(0).setHeight(350);
      table.getRow(0).getCtRow().getTrPr().getTrHeightArray(0).setHRule(STHeightRule.EXACT);
      for (int i = 0; i < 5; ++i)
         table.getRow(0).getCell(i).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);

      int counter = 1;
      for (String usn: subject.getUsnList()) {
         usnColumn = table.createRow().getCell(0).getParagraphArray(0);
         usnColumn.setAlignment(ParagraphAlignment.LEFT);
         writeRun(usnColumn, usn, false);
         table.getRow(counter).setHeight(350);
         table.getRow(counter).getCtRow().getTrPr().getTrHeightArray(0).setHRule(STHeightRule.EXACT);
         table.getRow(counter).getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
         ++counter;
      }

      CTTbl ctable = table.getCTTbl();
      CTTblPr pr = ctable.getTblPr();
      CTTblWidth tblW = pr.getTblW();
      tblW.setW(BigInteger.valueOf(5000));
      tblW.setType(STTblWidth.PCT);
      pr.setTblW(tblW);
      ctable.setTblPr(pr);
   }

   public void createDoc() throws Exception {

      //Write the Document in file system
      FileOutputStream out = new FileOutputStream( new File("bform-" + date + time + ".docx"));

      for (RoomBean roomBean: detailedRoomMap.keySet()) {
         ArrayList<SubjectStudentUsn> subjectList = detailedRoomMap.get(roomBean);
         for (SubjectStudentUsn subject: subjectList) {
            writeHeading();
            writeCourseDetails(subject);
            document.createParagraph().createRun().addBreak();
            writeUSN(subject);
         }
      }

      document.write(out);
      out.close();

   }
}