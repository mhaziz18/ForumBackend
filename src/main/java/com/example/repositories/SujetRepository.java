package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Sujet;

public interface SujetRepository extends JpaRepository<Sujet, Long> {

	
}
