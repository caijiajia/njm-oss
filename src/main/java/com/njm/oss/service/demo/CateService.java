package com.njm.oss.service.demo;

import java.util.List;

import com.njm.oss.model.demo.Cate;


public interface CateService {

	public Cate queryInfoCateById(int purchase_id);

	public Cate queryInfoByCateId(int purchase_id);

	public List<Cate> queryListByParentId(int purchase_id);

}
