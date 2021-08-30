package com.qa.democart.pages;

import org.openqa.selenium.By;

public class CartPage {

	By cart=By.id("cart");
	
	public void addToCart() {
		System.out.println("Add the product to the cart");
	}
	
}
