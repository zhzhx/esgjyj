package com.eastsoft.esgjyj.controller;

import java.util.*;

import com.eastsoft.esgjyj.dao.OfficeMapper;
import com.eastsoft.esgjyj.dao.UserMapper;
import com.eastsoft.esgjyj.util.*;
import com.eastsoft.esgjyj.vo.YjkhKhdxVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.eastsoft.esgjyj.domain.YjkhKhdxDO;
import com.eastsoft.esgjyj.service.YjkhKhdxService;

/**
 * ${comments}
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date November 13, 2017 3:18:15 PM CST
 */

@RestController
@RequestMapping("/esgjyj/yjkhKhdx")
public class YjkhKhdxController {
    @Autowired
    private YjkhKhdxService yjkhKhdxService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OfficeMapper officeMapper;

    @GetMapping("/get/{id}")
    YjkhKhdxVO get(@PathVariable("id") String id) {
        YjkhKhdxDO yjkhKhdx = yjkhKhdxService.get(id);
        YjkhKhdxVO yjkhKhdxVO = new YjkhKhdxVO();
        yjkhKhdxVO.setId(yjkhKhdx.getId());
        if(null!=yjkhKhdx.getUserid()){
            yjkhKhdxVO.setName(userMapper.selectByPrimaryKey(yjkhKhdx.getUserid()).getUsername());
        }
        yjkhKhdxVO.setDxtype(yjkhKhdx.getDxtype());
        yjkhKhdxVO.setMbjas(yjkhKhdx.getMbjas());
        yjkhKhdxVO.setOfficeid(yjkhKhdx.getOfficeid());
        if(null!=yjkhKhdx.getOfficeid()&&null!=officeMapper.selectByPrimaryKey(yjkhKhdx.getOfficeid())){
            yjkhKhdxVO.setOfficeName(officeMapper.selectByPrimaryKey(yjkhKhdx.getOfficeid()).getShortname());
        }
        if(null!=yjkhKhdx.getDxtype()){
            yjkhKhdxVO.setTypeName(yjkhKhdxService.dxlbConvert(yjkhKhdx.getDxtype()));
        }

        yjkhKhdxVO.setUserid(yjkhKhdx.getUserid());
        yjkhKhdxVO.setKhid(yjkhKhdx.getKhid());
        return yjkhKhdxVO;
    }

    @GetMapping("/list/{khid}")
    public List<YjkhKhdxVO> list(@PathVariable("khid") String khid) {
        //查询列表数据
        Map<String,Object> params = new HashMap<>();
        params.put("khid",khid);
        List<YjkhKhdxDO> yjkhKhdxList = yjkhKhdxService.list(params);
        List<YjkhKhdxVO> res = new ArrayList<>();
        for (YjkhKhdxDO yjkhKhdx : yjkhKhdxList) {
            YjkhKhdxVO yjkhKhdxVO = new YjkhKhdxVO();
            yjkhKhdxVO.setId(yjkhKhdx.getId());
            if(null!=yjkhKhdx.getUserid()){
                yjkhKhdxVO.setName(userMapper.selectByPrimaryKey(yjkhKhdx.getUserid()).getUsername());
            }
            yjkhKhdxVO.setDxtype(yjkhKhdx.getDxtype());
            yjkhKhdxVO.setMbjas(yjkhKhdx.getMbjas());
            yjkhKhdxVO.setOfficeid(yjkhKhdx.getOfficeid());
            if(null!=yjkhKhdx.getOfficeid()&&null!=officeMapper.selectByPrimaryKey(yjkhKhdx.getOfficeid())){
                yjkhKhdxVO.setOfficeName(officeMapper.selectByPrimaryKey(yjkhKhdx.getOfficeid()).getShortname());
            }
            if(null!=yjkhKhdx.getDxtype()){
                yjkhKhdxVO.setTypeName(yjkhKhdxService.dxlbConvert(yjkhKhdx.getDxtype()));
            }
            res.add(yjkhKhdxVO);
        }
        return res;
    }

    @GetMapping("/list")
    public List<YjkhKhdxVO> listNotRest( String khid,String zbid,String dxtype,String selfOffice) {
        //查询列表数据
        Map<String,Object> params = new HashMap<>();
        params.put("khid",khid);
        if(StringUtils.isNotBlank(zbid)){
            params.put("dxtype",zbid.substring(0,zbid.lastIndexOf("-")));
            if("true".equals(selfOffice)){
                params.put("officeid", ShiroUtils.getUser().getOfficeid());
            }
        }
        if(StringUtils.isNotBlank(dxtype)){
            params.put("dxtype",dxtype);
        }
        List<YjkhKhdxDO> yjkhKhdxList = yjkhKhdxService.list(params);
        List<YjkhKhdxVO> res = new ArrayList<>();
        for (YjkhKhdxDO yjkhKhdx : yjkhKhdxList) {
            YjkhKhdxVO yjkhKhdxVO = new YjkhKhdxVO();
            yjkhKhdxVO.setId(yjkhKhdx.getId());
            if(StringUtils.isNotBlank(yjkhKhdx.getUserid())){
                yjkhKhdxVO.setName(userMapper.selectByPrimaryKey(yjkhKhdx.getUserid()).getUsername());
            }
            yjkhKhdxVO.setDxtype(yjkhKhdx.getDxtype());
            yjkhKhdxVO.setMbjas(yjkhKhdx.getMbjas());
            yjkhKhdxVO.setOfficeid(yjkhKhdx.getOfficeid());
            if(null!=yjkhKhdx.getOfficeid()&&null!=officeMapper.selectByPrimaryKey(yjkhKhdx.getOfficeid())){
                yjkhKhdxVO.setOfficeName(officeMapper.selectByPrimaryKey(yjkhKhdx.getOfficeid()).getShortname());
            }
            if(null!=yjkhKhdx.getDxtype()){
                yjkhKhdxVO.setTypeName(yjkhKhdxService.dxlbConvert(yjkhKhdx.getDxtype()));
            }
            res.add(yjkhKhdxVO);
        }
        return res;
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(YjkhKhdxDO yjkhKhdx,@RequestParam("ids") String[] ids) {

        yjkhKhdx.setPxh(0);
        for( String id:ids){
            yjkhKhdx.setId(UUID.randomUUID().toString().replace("-", ""));
            yjkhKhdx.setUserid(id.replace("user_",""));
            yjkhKhdxService.save(yjkhKhdx);
        }
//        if (yjkhKhdxService.save(yjkhKhdx) > 0) {
//            return R.ok();
//        }
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(YjkhKhdxDO yjkhKhdx) {
        yjkhKhdx.setUserid(yjkhKhdx.getUserid().replace("user_",""));
        yjkhKhdxService.update(yjkhKhdx);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    public R remove(String id) {
        if (yjkhKhdxService.remove(id) > 0) {
            return R.ok();
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    public R remove(@RequestParam("ids[]") String[] ids) {
        yjkhKhdxService.batchRemove(ids);
        return R.ok();
    }

    @RequestMapping ("/copy")
    public R copy(String khid){
        yjkhKhdxService.copyAll(khid);
        return R.ok();
    }
    @GetMapping("/listOffice")
    List<Map<String, Object>> listOffice(){
        return yjkhKhdxService.listOffice();
    }
}
