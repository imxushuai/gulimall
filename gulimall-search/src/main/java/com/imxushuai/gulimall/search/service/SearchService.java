package com.imxushuai.gulimall.search.service;

import com.imxushuai.gulimall.search.vo.SearchParam;
import com.imxushuai.gulimall.search.vo.SearchResult;

public interface SearchService {

	/**
	 * 检索所有参数
	 */
	SearchResult search(SearchParam Param);
}