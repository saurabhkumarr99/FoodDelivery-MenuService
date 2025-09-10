package com.fooddelivery.menuservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fooddelivery.menuservice.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

	// get menu by id
	Optional<Menu> findMenuByMenuId(Integer menuId);
}