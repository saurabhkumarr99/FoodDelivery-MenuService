package com.fooddelivery.menuservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.menuservice.exception.MenuNotFoundException;
import com.fooddelivery.menuservice.model.Menu;
import com.fooddelivery.menuservice.repository.MenuRepository;

@Service
public class MenuService {

	private static final Logger logger = LoggerFactory.getLogger(MenuService.class);

	@Autowired
	private MenuRepository menuRepository;

	/**
	 * @author Saurabh Rai
	 * @apiNote Register menu
	 * @param menu
	 * @return
	 */
	public Menu registerMenu(Menu menu) {
		return menuRepository.save(menu);
	}

	/**
	 * @author Saurabh Rai
	 * @apiNote Get Menu by id
	 * @param menuId
	 * @return
	 * @throws MenuNotFoundException
	 */
	public Menu getMenuById(int menuId) throws MenuNotFoundException {
		Optional<Menu> menu = menuRepository.findMenuByMenuId(menuId);
		if (menu.isPresent()) {
			return menu.get();
		} else {
			throw new MenuNotFoundException("Menu Not found with ID: " + menuId);
		}
	}

	/**
	 * @author Saurabh Rai
	 * @apiNote Gey all menus
	 * @return
	 */
	public List<Menu> getAllMenu() {
		return menuRepository.findAll();
	}

	/**
	 * @author Saurabh Rai
	 * @apiNote Update Menu
	 * @param menu
	 * @return
	 * @throws MenuNotFoundException
	 */
	public Menu updateMenu(Menu menu) throws MenuNotFoundException {
		Optional<Menu> existing = menuRepository.findMenuByMenuId(menu.getMenuId());
		if (existing.isPresent()) {
			return menuRepository.save(menu);
		} else {
			throw new MenuNotFoundException("Menu Not Exist with ID: " + menu.getMenuId());
		}
	}

	/**
	 * @author Saurabh Rai
	 * @apiNote Delete Menu
	 * @param id
	 * @return
	 * @throws MenuNotFoundException
	 */
	public Menu deleteMenu(int id) throws MenuNotFoundException {
		Optional<Menu> existing = menuRepository.findMenuByMenuId(id);
		if (existing.isPresent()) {
			menuRepository.deleteById(existing.get().getMenuId());
			return existing.get();
		} else {
			throw new MenuNotFoundException("Menu Not Exist with ID: " + id);
		}
	}
}