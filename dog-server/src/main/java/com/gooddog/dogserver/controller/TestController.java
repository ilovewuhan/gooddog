package com.gooddog.dogserver.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.text.StrBuilder;
import com.gooddog.base.exception.ServiceException;
import com.gooddog.base.resp.JsonProtocol;
import com.gooddog.dogserver.po.InStockPo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * @author zlh
 * @date 2021/4/28 14:18
 * @description
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/import")
    public JsonProtocol importExcel(@RequestParam("file") MultipartFile file) {
        ImportParams importParams = new ImportParams();
        Workbook workbook = null;
        importParams.setTitleRows(1);
        try {
            InputStream is = file.getInputStream();
          /*  if (file.getOriginalFilename().endsWith("xlsx")) {
                workbook = new XSSFWorkbook(is);
                return JsonProtocol.failed();
            }*/
            List<InStockPo> list = ExcelImportUtil.importExcel(is, InStockPo.class, importParams);
            return JsonProtocol.success(list);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("io异常");
        } catch (Exception e) {
            throw new ServiceException("解析异常");
        }
    }

    @PostMapping("/genFile")
    public void genFile(@RequestParam MultipartFile fileParam) {
        StringBuilder strBuilder = new StringBuilder("\r\ninsert into table (`name`,`value`) value ");
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;
        StrBuilder text = StrBuilder.create();
        String lineText;
        try {
            InputStream is = fileParam.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(is));
            while ( (lineText = bufferedReader.readLine()) != null) {
                text.append(lineText);
            }
            bufferedReader.close();
            if (text.length() > 0) {
                String[] split = text.toString().split("，");
                for (String s : split) {
                    strBuilder.append("('").append(s).append("',1),\r\n");
                }
                // 最后一个,的索引
                int index = strBuilder.toString().lastIndexOf(",");
                strBuilder.replace(index, index + 1, ";");

            }
            // 本地是否存在此文件
            File localFile = new File("D:/a.sql");
            if (!localFile.exists()) {
                localFile.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(localFile, true);
            printWriter = new PrintWriter(fos);
            printWriter.append(strBuilder);
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

}
