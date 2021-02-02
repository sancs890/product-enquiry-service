package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.ProductEnquiryBean;
import com.example.demo.client.ProductStockClient;

@RestController
public class ProductEnquiryController {
	@Autowired
	ProductStockClient stockClient;
	@GetMapping("product-enquiry/productname/{name}/availability/{availability}/unit/{unit}")
	public ProductEnquiryBean enuqireProducts(@PathVariable String name,
												@PathVariable String availability,
												@PathVariable int unit) {
		ProductEnquiryBean productEnquiryBean = stockClient.checkProductStock(name, availability);
		double totalPrice = productEnquiryBean.getProductPrice().doubleValue()*unit;
		double discuontedPrice = totalPrice - totalPrice*productEnquiryBean.getDiscountOffer()/100;
		productEnquiryBean.setTotalPrice(totalPrice);
		productEnquiryBean.setDiscountPrice(discuontedPrice);
		productEnquiryBean.setUnit(unit);
		
		return productEnquiryBean;
	}
}
