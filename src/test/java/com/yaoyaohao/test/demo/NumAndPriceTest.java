package com.yaoyaohao.test.demo;

import com.alibaba.fastjson.JSON;
import com.njm.framework.data.DataMap;
import com.njm.framework.data.IData;

public class NumAndPriceTest {

	public static void dealNum(IData num) {

		System.out.println("传入数量信息 :" + JSON.toJSONString(num));

		int sales_type = num.getInt("sales_type", 0);
		int middle_num = num.getInt("middle_num", 1);
		int whole_num = num.getInt("whole_num", 1);
		int stock_num = num.getInt("stock_num", 0);
		int min_num = num.getInt("min_num", 1);
		int max_num = num.getInt("max_num", 0);

		num.put("code", 1);
		num.put("info", "处理成功");

		if (stock_num <= 0) {
			// 没有库存
			num.put("code", 0);
			num.put("info", "没有库存");
			return;
		}

		int step = 1;
		if (sales_type == 2) {
			step = whole_num;
		}
		else
			if (sales_type == 1) {
				step = middle_num;
			}
		num.put("step", step);

		if (min_num < step) {
			min_num = step;
		}

		// 结合步长计算最小购买数量
		if (min_num > 1 && step != 1) {
			min_num = (int) Math.ceil((double) min_num / (double) step) * step;
		}
		num.put("min_num", min_num);

		if (min_num > stock_num) {
			// 最小购买数量超过没有库存
			num.put("code", 0);
			num.put("info", "最小购买数量超过库存，无足够可售库存");
			return;
		}

		if (max_num > 0) {

			// 当最大购买数量超过库存是，将最大购买数量设置为库存
			max_num = (max_num > stock_num) ? stock_num : max_num;

			if (max_num < min_num) {
				// max_num = -1;
				// 最大购买数量大于最小购买数量
				num.put("code", 0);
				num.put("info", "最大购买数量小于最小购买数量");
				return;
			}
			else {
				max_num = (int) Math.floor((double) max_num / (double) step) * step;
			}
		}
		num.put("max_num", max_num);
	}

	public static void dealPrice(IData price, IData num) {

		System.out.println("传入价格信息 :" + JSON.toJSONString(price));
		double retail_price = price.getDouble("retail_price", 0);
		double middle_price = price.getDouble("middle_price", 0);
		double whole_price = price.getDouble("whole_price", 0);
		price.put("type", 0);
		if (price.containsKey("promotionalPrice")) {
			double promotionalPrice = price.getDouble("promotionalPrice", 0);
			price.put("type", 2);
			price.put("init_price", promotionalPrice);
			return;
		}
		else
			if (price.containsKey("regionPrice")) {
				double regionPrice = price.getDouble("regionPrice", 0);
				price.put("type", 1);
				price.put("init_price", regionPrice);
				return;
			}

		int min_num = num.getInt("min_num", 1);
		int middle_num = num.getInt("middle_num", 1);
		int whole_num = num.getInt("whole_num", 1);

		if (min_num >= whole_num) {
			price.put("init_price", whole_price);
		}
		else
			if (min_num >= middle_num) {
				price.put("init_price", middle_price);
			}
			else {
				price.put("init_price", retail_price);
			}

	}

	public static void main(String[] args) {

		System.out.println("向上取整 :" + Math.ceil((double) 5 / (double) 4));

		IData num = new DataMap();

		num.put("sales_type", 0);
		// num.put("sales_type", 1);
		// num.put("sales_type", "2");

		num.put("middle_num", 10);
		num.put("whole_num", 100);

		// num.put("stock_num", 0);
		num.put("stock_num", 10);
		// num.put("stock_num", 200);

		// num.put("min_num", 1);
		num.put("min_num", 15);

		num.put("max_num", 0);
		// num.put("max_num", 9);
		// num.put("max_num", 20);

		dealNum(num);

		System.out.println("处理后 数量信息 :" + JSON.toJSONString(num));

		IData price = new DataMap();
		price.put("retail_price", 50.00);
		price.put("middle_price", 40.00);
		price.put("whole_price", 30.00);
		// price.put("regionPrice", 20.00);
		// price.put("promotionalPrice", 10.00);

		dealPrice(price, num);
		System.out.println("处理后 价格信息 :" + JSON.toJSONString(price));

		// testType();
	}

	public static void testType() {
		IData data = new DataMap();

		data.put("int1", "");
		data.put("int2", "aa");
		data.put("int3", "11.11");
		data.put("int4", "11");

		System.out.println("int :" + data.getInt("int"));
		// System.out.println("int1 :" + data.getInt("int1"));
		// System.out.println("int1 :" + data.getInt("int1", 0));//不行
		// System.out.println("int2 :" + data.getInt("int2"));//不行
		// System.out.println("int3 :" + data.getInt("int3"));//不行
		System.out.println("int4 :" + data.getInt("int4"));// 不行
	}
}
