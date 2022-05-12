package com.bl.ep.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ExcelUtil
 * @Description TODO
 * @Author 陈宝梁
 * @Date 2021/11/26 16:41
 * @Version 1.0
 **/
public class ExcelUtil {
    /**
     * 导出Excel
     * @param result
     * @param headArray 表头数组（有序）
     * @param dataArray 字段数组（有序）
     * @return
     */
    public static Workbook exportExcel(List<Map<String,Object>> result, String[] headArray, String[] dataArray){

        //表示SXSSFWorkbook只会保留100条数据在内存中，避免内存溢出
        Workbook wb = new SXSSFWorkbook(1000);

        //1页面
        Sheet sheet = wb.createSheet("Sheet1");
        //2行标题
        Row row = sheet.createRow(0);
        Cell cell = null;
        for (int i = 0; i < headArray.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(headArray[i]);
        }

        Map<String,Object> map = null;
        for (int j = 0; j < result.size(); j++) {
            map = result.get(j);
            row = sheet.createRow(j + 1);

            for(int k = 0; k < headArray.length; k++ ){
                cell = row.createCell(k);
                cell.setCellValue(map.get(dataArray[k]) + "");

            }
        }

        return wb;
    }


}
