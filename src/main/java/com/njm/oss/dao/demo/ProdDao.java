package com.njm.oss.dao.demo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.njm.oss.model.demo.Prod;

@Repository
public interface ProdDao {

	public Prod selectInfoById(int merchandise_id);

	public Prod selectInfoByProdId(String merchandise_cd);

	public List<Prod> selectListByCateId(int purchase_id);

	public List<Prod> selectListByPageParam1(int purchase_id, int start, int size);

	public List<Prod> selectListByPageParam2(@Param("purchase_id") int purchase_id, @Param("start") int start,
			@Param("size") int size);

	public List<Prod> selectListByPageParam3(Map<String, Object> param);

	public List<Prod> selectListByPageParam4(Map<String, Object> param);

}
