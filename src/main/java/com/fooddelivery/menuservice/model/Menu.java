package com.fooddelivery.menuservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int menuId;

	@NotBlank(message = "Menu name is required")
	private String menuName;

	@NotNull(message = "Amount is required")
	@Min(value = 1, message = "Amount must be greater than 0")
	private long amount;

	@NotBlank(message = "Description is required")
	private String menuDescription;

	public Menu() {

	}

	public Menu(int menuId, @NotBlank(message = "Menu name is required") String menuName,
			@NotNull(message = "Amount is required") @Min(value = 1, message = "Amount must be greater than 0") long amount,
			@NotBlank(message = "Description is required") String menuDescription) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.amount = amount;
		this.menuDescription = menuDescription;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getMenuDescription() {
		return menuDescription;
	}

	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", menuName=" + menuName + ", amount=" + amount + ", menuDescription="
				+ menuDescription + "]";
	}

}
