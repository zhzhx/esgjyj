package com.eastsoft.esgjyj.service;

import com.eastsoft.esgjyj.domain.YjkhDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ${comments}
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date November 13, 2017 8:56:59 AM CST
 */
@Service
public interface YjkhService {
	
	YjkhDO get(String id);
	
	List<YjkhDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(YjkhDO yjkh);
	
	int update(YjkhDO yjkh);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	int init();
}
