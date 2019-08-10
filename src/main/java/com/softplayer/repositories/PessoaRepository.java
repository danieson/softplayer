package com.softplayer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softplayer.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
