package com.fooddelivery.menuservice.dto;

import com.fooddelivery.menuservice.model.Menu;

public class MenuDto {
	private Integer menuId;
	private String menuName;
	private long amount;
	private String menuDescription;

	public MenuDto() {
	}

	public MenuDto(Integer menuId, String menuName, long amount, String menuDescription) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.amount = amount;
		this.menuDescription = menuDescription;
	}

	public MenuDto(Menu menu) {
		this.menuId = menu.getMenuId();
		this.menuName = menu.getMenuName();
		this.amount = menu.getAmount();
		this.menuDescription = menu.getMenuDescription();
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuDescription() {
		return menuDescription;
	}

	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "MenuDto [menuId=" + menuId + ", menuName=" + menuName + ", amount=" + amount + ", menuDescription="
				+ menuDescription + "]";
	}

}