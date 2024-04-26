package com.proyecto.marketin.model;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "empleados", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class Empleado implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Integer id;
	@Basic
	@Column(nullable = false)
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private String address;
	private String numberphone;
	
	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Perfil.class, cascade = CascadeType.MERGE)
	@JoinTable(name = "Empleado_Perfil", joinColumns = @JoinColumn(name = "id_Username"), inverseJoinColumns = @JoinColumn(name="id_Perfil"))
	private Set<Perfil> perfiles;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return perfiles.stream().map(perfil -> new SimpleGrantedAuthority(perfil.getNombre())).collect(Collectors.toList());
	}
	

	
	public void setPerfiles(Set<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    public Set<Perfil> getPerfiles() {
        return perfiles;
    }
	public String getPassword() {

		return password;
	}
	
	public String getUsername() {

		return username;
	}
	@Override
	public boolean isAccountNonExpired() {

		return true;
	}
	@Override
	public boolean isAccountNonLocked() {

		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}
	@Override
	public boolean isEnabled() {

		return true;
	}



	
}

