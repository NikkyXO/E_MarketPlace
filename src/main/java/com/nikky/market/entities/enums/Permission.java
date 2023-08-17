package com.nikky.market.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public enum Permission {

	ADMIN_READ("admin:read"),
	ADMIN_WRITE("admin:write"),
	ADMIN_CREATE("admin:create"),
	ADMIN_DELETE("admin:update"),
	
	MANAGER_READ("manager:read"),
	MANAGER_WRITE("manager:write"),
	MANAGER_CREATE("manager:create"),
	MANAGER_DELETE("manager:update")
	;
	
	@Getter
	private final String permission;
	
	
}
