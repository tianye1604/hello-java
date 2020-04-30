package com.study.anything;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianshujian
 * Create Date: 2020/3/20 11:46
 * Description: ${DESCRIPTION}
 */
public class TestDoWhile {

	private static final Integer PER_SIZE = 20;

	public static void main(String[] args) {
		List<Long> productIdList = new ArrayList<>();
		Integer count = 19;
		for (Integer i = 0; i < count; i++) {
			productIdList.add(i.longValue());
		}

		Integer subSize = productIdList.size() > PER_SIZE ? PER_SIZE : productIdList.size();
		Integer start = 0;
		Integer end = subSize;
		/**
		 * 开启多线程、二次分片、处理目标活动品ID
		 */
		do {
			List<Long> subProductIdList = productIdList.subList(start,end);
//			executorService.execute(new RefreshNormalStockRelateRequest(subProductIdList, activityProductService,
//					activityInfoService, activityPoolRelateService, poolProductRelateService, skuStockCacheService,
//					currentCacheMqInfoService, currentCacheInfoService));
			System.out.println(JSON.toJSON(subProductIdList));
			start = end;
			count = count - subSize;
			if (count < subSize) {
				end = start + count.intValue();
			} else {
				end = start + subSize;
			}
		} while (count > 0);
	}
}
