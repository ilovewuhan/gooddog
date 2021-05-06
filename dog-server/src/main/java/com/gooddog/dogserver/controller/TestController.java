package com.gooddog.dogserver.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.gooddog.base.exception.ServiceException;
import com.gooddog.base.resp.JsonProtocol;
import com.gooddog.dogserver.po.InStockPo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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

}
