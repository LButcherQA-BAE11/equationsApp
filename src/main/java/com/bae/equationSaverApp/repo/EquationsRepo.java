package com.bae.equationSaverApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.equationSaverApp.domain.Equations;

@Repository
public interface EquationsRepo extends JpaRepository<Equations, Long> {

}
