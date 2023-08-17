package com.nikky.market.entities.enums;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum Role {
	
	USER(Collections.EMPTY_SET),
	
	ADMIN(
			Set.of(
				Permission.ADMIN_READ,
				Permission.ADMIN_WRITE,
				Permission.ADMIN_DELETE,
				Permission.ADMIN_CREATE,
				
				Permission.MANAGER_CREATE,
				Permission.MANAGER_DELETE,
				Permission.MANAGER_READ,
				Permission.MANAGER_WRITE
					)
			),
	
	MANAGER(
			Set.of(
					Permission.MANAGER_CREATE,
					Permission.MANAGER_READ,
					Permission.MANAGER_WRITE,
					Permission.MANAGER_DELETE
					)
			)
	
	;
	
	@Getter
	private final Set<Permission> permissions;
	
	public List<SimpleGrantedAuthority> getAuthories() {
		var authorities = getPermissions().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
				.collect(Collectors.toList());
		
		authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return authorities;
	}
	
	
}
