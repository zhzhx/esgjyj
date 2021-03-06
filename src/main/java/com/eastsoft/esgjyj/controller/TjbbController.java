package com.eastsoft.esgjyj.controller;

import com.eastsoft.esgjyj.dao.YjkhDao;
import com.eastsoft.esgjyj.domain.YjkhDO;
import com.eastsoft.esgjyj.service.CbsptReportService;
import com.eastsoft.esgjyj.service.FgzlReportService;
import com.eastsoft.esgjyj.service.SjyReportService;
import com.eastsoft.esgjyj.service.impl.TjbbServiceImpl;
import com.eastsoft.esgjyj.util.DateUtil;
import com.eastsoft.esgjyj.vo.FgkpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**

 */

@RequestMapping("/tjbb")
@RestController
public class TjbbController {
    @Autowired
    TjbbServiceImpl tjbbService;
    @Autowired
    CbsptReportService cbsptReportService;

    @Autowired
    FgzlReportService fgzlReportService;

    @Autowired
    SjyReportService sjyReportService;

    @Autowired
    YjkhDao yjkhDao;
    @GetMapping("/fgkp/list")
    List<FgkpVO> fgkpList(String khid,String ofid){
        return  tjbbService.listFgKp(khid,ofid);
    }
    @GetMapping("/tzkp/list")
    List<FgkpVO> tzkpList(String khid){
        return  tjbbService.listTzKp(khid);
    }
    @GetMapping("/zhkp/list")
    List<FgkpVO> zhkpList(String khid){
        return  tjbbService.listZhKp(khid);
    }
    @GetMapping("/zhfzr/list")
    List<FgkpVO> zhfzrList(String khid){
        return  tjbbService.listZhfzr(khid);
    }
    @GetMapping("/zhzl/list")
    List<FgkpVO> zhzlList(String khid){
        return  tjbbService.listZhzl(khid);
    }
    @GetMapping("/zhsjy/list")
    List<FgkpVO> zhsjyList(String khid){
        return  tjbbService.listZhsjy(khid);
    }

    @GetMapping("/zlkp/list")
    List<FgkpVO> zlkpList(String khid){
        return  tjbbService.listZlKp(khid);
    }

    @GetMapping("/sjykp/list")
    List<FgkpVO> sjykpList(String khid){
        return  tjbbService.listSjyKp(khid);
    }

    @GetMapping("/prsjy/list")
    List<FgkpVO> prsjyList(String khid){
        return  tjbbService.listPrsjy(khid);
    }

    @GetMapping("/cbspt/list")
    List<Map<String, Object>> cbspt(String khid){
        YjkhDO yjkhDO = yjkhDao.get(khid);
        String ksyf = yjkhDO.getKsyf();
        String jsyf = yjkhDO.getJsyf();
        String ksrq="",jzrq="";
        ksrq = ksyf+"-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date jzrqDate = null;
        try {
            jzrqDate = sdf.parse(jsyf);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        jzrq = sdf2.format(DateUtil.getLastDayOfMonth(jzrqDate));
        Map<String,Object> map = new HashMap<>();
        map.put("ksrq",ksrq);
        map.put("jzrq",jzrq);
        map.put("khid",khid);
        List<Map<String, Object>> list = cbsptReportService.cbrList(map);
        return list;
    }

    @GetMapping("/fgzlRe/list")
    List<Map<String, Object>> fgzlRe(String khid){
        YjkhDO yjkhDO = yjkhDao.get(khid);
        String ksyf = yjkhDO.getKsyf();
        String jsyf = yjkhDO.getJsyf();
        String ksrq="",jzrq="";
        ksrq = ksyf+"-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date jzrqDate = null;
        try {
            jzrqDate = sdf.parse(jsyf);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        jzrq = sdf2.format(DateUtil.getLastDayOfMonth(jzrqDate));
        Map<String,Object> map = new HashMap<>();
        map.put("ksrq",ksrq);
        map.put("jzrq",jzrq);
        map.put("khid",khid);
        List<Map<String, Object>> list = fgzlReportService.fgzlList(map);
        return list;
    }

    @GetMapping("/sjyRe/list")
    List<Map<String, Object>> sjyRe(String khid){
        YjkhDO yjkhDO = yjkhDao.get(khid);
        String ksyf = yjkhDO.getKsyf();
        String jsyf = yjkhDO.getJsyf();
        String ksrq="",jzrq="";
        ksrq = ksyf+"-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date jzrqDate = null;
        try {
            jzrqDate = sdf.parse(jsyf);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        jzrq = sdf2.format(DateUtil.getLastDayOfMonth(jzrqDate));
        Map<String,Object> map = new HashMap<>();
        map.put("ksrq",ksrq);
        map.put("jzrq",jzrq);
        map.put("khid",khid);
        List<Map<String, Object>> list = sjyReportService.sjyList(map);
        return list;
    }
}
