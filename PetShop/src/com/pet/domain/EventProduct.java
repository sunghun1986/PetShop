package com.pet.domain;

public class EventProduct {
	
	private int event_product_id;
	private Event event_id;
	private Product product_id;
	
	public int getEvent_product_id() {
		return event_product_id;
	}
	public void setEvent_product_id(int event_product_id) {
		this.event_product_id = event_product_id;
	}
	public Event getEvent_id() {
		return event_id;
	}
	public void setEvent_id(Event event_id) {
		this.event_id = event_id;
	}
	public Product getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Product product_id) {
		this.product_id = product_id;
	}
	
	

}
