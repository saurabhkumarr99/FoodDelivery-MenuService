package com.fooddelivery.menuservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.menuservice.dto.MenuDto;
import com.fooddelivery.menuservice.exception.MenuNotFoundException;
import com.fooddelivery.menuservice.model.Menu;
import com.fooddelivery.menuservice.service.MenuService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/menuService")
public class MenuController {

	@Autowired
	MenuService menuService;

	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

	/**
	 * @author Saurabh Rai
	 * @apiNote Register Menu
	 * @param menu
	 * @return
	 */
	@PostMapping("/registerMenu")
	public ResponseEntity<?> registerMenu(@Valid @RequestBody Menu menu, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(errors);
		}

		Menu menu2 = null;

		try {
			menu2 = menuService.registerMenu(menu);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", e.getLocalizedMessage()));
		}

		logger.info("Menu Registered Succesfully : " + menu);
		MenuDto menuDto = new MenuDto(menu2);
		return ResponseEntity.status(HttpStatus.OK).body(menuDto);
	}

	/**
	 * @author Saurabh Rai
	 * @apiNote Get menu by id
	 * @param id
	 * @return
	 */
	@GetMapping("/getMenuById/{id}")
	public ResponseEntity<?> getMenuById(@PathVariable("id") int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(menuService.getMenuById(id));
		} catch (MenuNotFoundException e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getLocalizedMessage()));
		}
	}

	/**
	 * @author Saurabh Rai
	 * @apiNote Get all menus
	 * @return
	 */
	@GetMapping("/getAllMenus")
	public ResponseEntity<?> getAllMenu() {
		return ResponseEntity.status(HttpStatus.OK).body(menuService.getAllMenu());
	}

	/**
	 * @author Saurabh Rai
	 * @apiNote Update menu
	 * @param menu
	 * @return
	 */
	@PutMapping("/updateMenu")
	public ResponseEntity<?> updateMenu(@Valid @RequestBody Menu menu, BindingResult bindingResult) {

		if (menu.getMenuId() == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Menu Id can not be null"));
		}
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(errors);
		}

		try {
			Menu menu2 = menuService.updateMenu(menu);
			logger.info("Menu Update successfully : " + menu2);

		} catch (MenuNotFoundException e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getLocalizedMessage()));
		}
		MenuDto menuDto = new MenuDto(menu);
		return ResponseEntity.status(HttpStatus.OK).body(menuDto);
	}

	/**
	 * @author Saurabh Rai
	 * @apiNote Delete Menu
	 * @param id
	 * @return
	 */
	@DeleteMapping("/deleteMenu/{id}")
	public ResponseEntity<?> deleteMenu(@PathVariable("id") int id) {
		Menu menu = null;
		try {
			menu = menuService.deleteMenu(id);
			logger.info("Menu deleted successfully : " + menu);
		} catch (MenuNotFoundException e) {
			logger.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getLocalizedMessage()));
		}
		return ResponseEntity.status(HttpStatus.OK).body(menu);

	}

}
