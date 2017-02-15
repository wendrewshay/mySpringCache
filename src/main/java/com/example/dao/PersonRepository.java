package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
