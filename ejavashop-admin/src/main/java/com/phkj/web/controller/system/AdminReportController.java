package com.phkj.web.controller.system;

import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.phkj.core.StringUtil;
import com.phkj.service.system.ICodeService;
import com.phkj.web.controller.BaseController;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 
 *                       
 * @Filename: AdminReportController.java
 * @Version: 1.0
 * @Author: 张斌 * @Email: 46584649@qq.com
 *
 */
@Controller
@RequestMapping(value = "admin/system/report")
public class AdminReportController extends BaseController {

    @Resource(name = "codeService")
    private ICodeService codeService;
    HttpServletResponse response = null;

//    @RequestMapping(value = "", method = { RequestMethod.POST })
    @RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.GET})
    public String data(String export, String sql, HttpServletResponse resp, Map<String, Object> dataMap) {
        if (StringUtil.isEmpty(sql)) 
            return "admin/report_custom/report";
        String inj_str = "exec :insert :delete :update :chr :mid :master :truncate :char :declare :;";
        String inj_stra[] = inj_str.split(":");
        for (int i = 0; i < inj_stra.length; i++) {
          if (sql.indexOf(inj_stra[i])!=-1) {
              return "admin/report_custom/report";
          }
        }
        this.response = resp;
        List<Map<String, Object>> list = (List<Map<String, Object>>) codeService.customReport(sql);
        String title = "报表";
        if (!StringUtil.isEmpty(export)) {
            exportExcel(list, title);
            return null;
        }
        else {
            dataMap.put("mapList", list);
            return "admin/report_custom/data.jsp";
        }
    }
    
    public static String getMethodName(String fieldName) {
        byte[] items = fieldName.getBytes();
        items[0] = (byte)((char)items[0]-'a'+'A');;
        return "get" + new String(items);
    }

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    private void exportExcel(List<Map<String, Object>> list, String excelTitle) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String theFileName = excelTitle + "_" + sdf.format(new Date()) + ".xls";
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(theFileName.getBytes(), "ISO8859_1"));
            OutputStream theStream = response.getOutputStream();
            WorkbookSettings ws = new WorkbookSettings();
            WritableWorkbook workbook = Workbook.createWorkbook(theStream, ws);
            WritableSheet sheet = workbook.createSheet(excelTitle, 0);
            int i = 0;
            int k = 0;
            // set Title
            WritableFont wf = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD); // 定义格式 字体 下划线 斜体 粗体 颜色
            WritableCellFormat wcfTitle = new WritableCellFormat(wf);
            wcfTitle.setAlignment(Alignment.CENTRE); // 设置垂直对齐
            if (list != null && list.size() > 0) {
                Map<String, Object> column = list.get(0);
                for (Map.Entry<String, Object> entry : column.entrySet()) {
                    String cn = entry.getKey();
                    Label l = new Label(i, k, cn, wcfTitle);
                    CellView cv = new CellView();
                    cv.setSize(5000); // 设置自动大小
    
                    sheet.addCell(l);
                    sheet.setColumnView(i, cv);
                    i++;
                }
                // set content
                k = 1;
                for (Map<String, Object> map : list) {
                    i = 0;
                    for (String dataKey : column.keySet()) {
                        Object dataValue = "";
                        try {
                            dataValue = map.get(dataKey);
                            if (dataValue instanceof Timestamp) {
                                if (dataValue.toString().contains("1900-01-01")) dataValue = "";            //处理返回日期为空时，sqlserver自动补充1900-01-01，故如果字段结果值中带有1900-01-01，则赋值空
                                else dataValue = dateFormat.format(dataValue);
                            }
                        } catch (Exception e) {
                            System.out.println("error map is " + map + "\nthe key is " + dataKey);
                            dataValue = "";
                        }
                        Label l = new Label(i, k, String.valueOf(dataValue));
                        sheet.addCell(l);
                        i++;
                    }
                    k++;
                }
            }
            workbook.write();
            workbook.close();
            theStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Terry 20140517
     * 
     * @param list
     * @param columnTitle
     * @param width
     * @param excelTitle
        List list = ibatisService.queryForList(sqlid, condition);
        Map <String, Object> column = new LinkedHashMap<String, Object>();  
        column.put("CODE", new String[]{"单号", "5000"}); 
        column.put("STARTTIME", new String[]{"创建时间", "5000"});  
        super.exportExcel(list, column, "事件单查询结果");
     */
    protected void exportExcel(List<Map<String, Object>> list, Map<String, Object> column, String excelTitle) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String theFileName = excelTitle + "_" + sdf.format(new Date()) + ".xls";
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(theFileName.getBytes(), "ISO8859_1"));
            OutputStream theStream = response.getOutputStream();
            WorkbookSettings ws = new WorkbookSettings();
            WritableWorkbook workbook = Workbook.createWorkbook(theStream, ws);
            WritableSheet sheet = workbook.createSheet(excelTitle, 0);
            int i = 0;
            int k = 0;
            // set Title
            WritableFont wf = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD); // 定义格式 字体 下划线 斜体 粗体 颜色
            WritableCellFormat wcfTitle = new WritableCellFormat(wf);
            wcfTitle.setAlignment(Alignment.CENTRE); // 设置垂直对齐
            for (Map.Entry<String, Object> entry : column.entrySet()) {
                String[] cn = (String[]) entry.getValue();
                Label l = new Label(i, k, cn[0], wcfTitle);
                CellView cv = new CellView();
                cv.setSize(Integer.parseInt(cn[1])); // 设置自动大小

                sheet.addCell(l);
                sheet.setColumnView(i, cv);
                i++;
            }
            // set content
            k = 1;
            for (Map<String, Object> map : list) {
                i = 0;
                for (String dataKey : column.keySet()) {
                    Object dataValue = "";
                    try {
                        dataValue = map.get(dataKey);
                        if (dataValue instanceof Timestamp) {
                            if (dataValue.toString().contains("1900-01-01")) dataValue = "";            //处理返回日期为空时，sqlserver自动补充1900-01-01，故如果字段结果值中带有1900-01-01，则赋值空
                            else dataValue = dateFormat.format(dataValue);
                        }
                    } catch (Exception e) {
                        System.out.println("error map is " + map + "\nthe key is " + dataKey);
                        dataValue = "";
                    }
                    Label l = new Label(i, k, String.valueOf(dataValue));
                    sheet.addCell(l);
                    i++;
                }
                k++;
            }
            workbook.write();
            workbook.close();
            theStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
