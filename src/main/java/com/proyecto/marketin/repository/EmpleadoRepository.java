package com.proyecto.marketin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.proyecto.marketin.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{

	boolean existsByUsername(String username);
	Optional<Empleado> findByUsername(String username);
}
