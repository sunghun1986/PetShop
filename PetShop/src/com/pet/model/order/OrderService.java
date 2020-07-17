package com.pet.model.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	@Autowired
	private OrderSummaryDAO orderSummaryDAO;
	@Autowired
	private OrderDetailDAO orderDetailDAO;

	//모든 주문목록 가져오기 
	public List selectAll() {
		return orderSummaryDAO.selectAll();
	}
	
	//해당 주문코드에 소속된 주문 상세내역 가져오기
	public List getOrderDetail(int order_summary_id) {
		return orderDetailDAO.selectAll(order_summary_id);
	}

}