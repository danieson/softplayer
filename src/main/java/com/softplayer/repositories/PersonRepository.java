package com.softplayer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softplayer.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
