package com.proyecto.marketin.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.marketin.model.Perfil;


public interface PerfilRepository extends JpaRepository<Perfil, Integer>{
	
	Optional<Perfil> findById(Integer id);

	Optional<Perfil> findByNombre(String nombre);

	
}
