package com.eastsoft.esgjyj.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eastsoft.esgjyj.dao.BaseDao;
import com.eastsoft.esgjyj.util.SftjUtil;
import com.eastsoft.esgjyj.util.Tools;

/**
 * 综合部门书记员绩效考核共5个指标（本类只计算客观指标）
 * @author zzx
 *
 */
@Service("zhbmSjyYjkhService")
public class ZhbmSjyYjkhServiceImpl {
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private GySpyjkhServiceImpl gySpyjkhService;
	
	/**
	 * 协助办结案件(第一个)
	 * @param khzj       考核主键
	 * @param khdx       考核对象id
	 * @param khdxbm     考核对象部门
	 * @param ksrq       开始日期
	 * @param jzrq       截止日期
	 * @return
	 */
	public double getXzbjas(String khzj, String khdxid, String khdx, String khdxbm, String ksrq, String jzrq, String dxtype) {
		String sql = "select * from YJKH_KHDX where KHID = '" + khzj + "' "
				+ " and OFFICEID = '" + khdxbm + "' and DXTYPE = '" + dxtype + "'";
		List<Map<String, Object>> sjyList = baseDao.queryForList(sql);
		String userid = "", khdxmc = "";
		List<Map<String, Object>> usernameList = null;
		int ajs = 0, zs = 0, khdxs = 0;
		Long sn = 0L;
		for(Map<String, Object> item : sjyList) {
			userid = (String)item.get("USERID");
			if(Tools.isEmpty(userid)) continue;
			sql = "select SN, SJY from CASES where SJYBS = '" + userid + "' "
					+ SftjUtil.generateBaseWhere("")
					+ SftjUtil.generateYjWhere(ksrq, jzrq, "");
			usernameList = baseDao.queryForList(sql);
			ajs = usernameList.size();
			if(userid.equals(khdx)) khdxs = ajs;
			zs += ajs;
		}
		for(Map<String, Object> item : sjyList) {
			userid = (String)item.get("USERID");
			if(Tools.isEmpty(userid)) continue;
			sql = "select SN, SJY from CASES where SJYBS = '" + userid + "' "
					+ SftjUtil.generateBaseWhere("")
					      + SftjUtil.generateYjWhere(ksrq, jzrq, "");
			usernameList = baseDao.queryForList(sql);
			for(Map<String, Object> map : usernameList) {
				if(userid.equals(khdx)) {
					khdxmc = (String)map.get("SJY");
					sn = map.get("SN") == null ? 0 : ((BigDecimal)map.get("SN")).longValue();
					if(sn != 0) gySpyjkhService.saveSn(khdxid, "3", dxtype, sn, 0.00, khdxmc + "协助办结案件!", zs * 1.0 / sjyList.size());
				}
			}
		}
		double tzf = 0.0, pjf = 0.0, bl = 0.0;
		if(sjyList.size() > 0) {
			pjf = zs * 1.0 / sjyList.size();
			if(pjf == 0) return 40.0;
			bl = (khdxs - pjf) / pjf;
			if(bl > 0) {
				while(bl - 0.1 > 0) {
					tzf += 2;
					bl -= 0.1;
				}
			} else {
				while(bl + 0.2 < 0) {
					tzf -= 2;
					bl += 0.2;
				}
			}
			double df = 40 + tzf;
			df = gySpyjkhService.decimal(df);
			if(df < 0) df = 0.00;
			return df;
		} else {
			return 40.00;
		}
	}
}
