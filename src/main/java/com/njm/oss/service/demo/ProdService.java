package com.njm.oss.service.demo;

import java.util.List;
import java.util.Map;

import com.njm.oss.model.demo.Prod;


public interface ProdService {

	public Prod getInfoById(int id);

	public Prod getInfoByProdId(String productId);

	public List<Prod> queryListByCateId(int categoryId);

	public Map<String, Object> queryListByPageParam(Map<String, Object> param);

}
