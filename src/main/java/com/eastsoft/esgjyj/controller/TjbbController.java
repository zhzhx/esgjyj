package com.eastsoft.esgjyj.controller;

import com.eastsoft.esgjyj.service.impl.TjbbServiceImpl;
import com.eastsoft.esgjyj.vo.FgkpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**

 */

@RequestMapping("/tjbb")
@RestController
public class TjbbController {
    @Autowired
    TjbbServiceImpl tjbbService;
    @GetMapping("/fgkp/list")
    List<FgkpVO> fgkpList(String khid){
        List<FgkpVO> fgkpVOS = new ArrayList<>();
        return  tjbbService.listFgKp(khid);
    }
    @GetMapping("/tzkp/list")
    List<FgkpVO> tzkpList(String khid){
        List<FgkpVO> fgkpVOS = new ArrayList<>();
        return  tjbbService.listTzKp(khid);
    }
    @GetMapping("/zhkp/list")
    List<FgkpVO> zhkpList(String khid){
        List<FgkpVO> fgkpVOS = new ArrayList<>();
        return  tjbbService.listZhKp(khid);
    }
    @GetMapping("/zhfzr/list")
    List<FgkpVO> zhfzrList(String khid){
        List<FgkpVO> fgkpVOS = new ArrayList<>();
        return  tjbbService.listZhfzr(khid);
    }
    @GetMapping("/zhzl/list")
    List<FgkpVO> zhzlList(String khid){
        List<FgkpVO> fgkpVOS = new ArrayList<>();
        return  tjbbService.listZhzl(khid);
    }
    @GetMapping("/zhsjy/list")
    List<FgkpVO> zhsjyList(String khid){
        List<FgkpVO> fgkpVOS = new ArrayList<>();
        return  tjbbService.listZhsjy(khid);
    }

    @GetMapping("/zlkp/list")
    List<FgkpVO> zlkpList(String khid){
        List<FgkpVO> fgkpVOS = new ArrayList<>();
        return  tjbbService.listZlKp(khid);
    }

    @GetMapping("/sjykp/list")
    List<FgkpVO> sjykpList(String khid){
        List<FgkpVO> fgkpVOS = new ArrayList<>();
        return  tjbbService.listSjyKp(khid);
    }

    @GetMapping("/prsjy/list")
    List<FgkpVO> prsjyList(String khid){
        List<FgkpVO> fgkpVOS = new ArrayList<>();
        return  tjbbService.listPrsjy(khid);
    }
}
