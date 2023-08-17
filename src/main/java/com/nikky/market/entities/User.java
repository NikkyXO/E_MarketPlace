package com.nikky.market.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nikky.market.entities.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="users")
public class User implements UserDetails {

//	@Serial
//	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	private String firstName;
	private String lastName;
	private String location;
	
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden=true)
	private String password;
	
	@Email
	@Column(unique=true)
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Role role;

	@JsonManagedReference // to avoid circular references, to be serialized normally
	@OneToMany(mappedBy = "user")
	private List<Token> tokens;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Notification> notifications;



	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getUsername() {
		return email;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	
}
