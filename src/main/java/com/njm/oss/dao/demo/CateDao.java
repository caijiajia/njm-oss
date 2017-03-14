package com.njm.oss.dao.demo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.njm.oss.model.demo.Cate;

@Repository
public interface CateDao {

	public Cate selectInfoById(int id);

	public Cate selectInfoByCateId(int categoryId);

	public List<Cate> selectListByParentId(int categoryId);

}
