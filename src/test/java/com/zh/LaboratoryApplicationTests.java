package com.zh;

import com.alibaba.fastjson.JSON;
import com.zh.bean.EventLog;
import com.zh.bean.result.DataResult;
import com.zh.service.DataService;
import com.zh.util.pdf.PDFCreateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LaboratoryApplication.class)
public class LaboratoryApplicationTests {

    @Resource
    DataService dataService;

    @Test
    public void contextLoads() {
        DataResult data = dataService.getData(new EventLog());
        System.out.println(JSON.toJSON(data));
    }
    @Test
    public void pdfCreate() throws FileNotFoundException {
        //new PDFCreateUtil(new FileOutputStream("test")).generatePDF();
    }
}
