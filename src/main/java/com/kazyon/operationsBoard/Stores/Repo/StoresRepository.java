package com.kazyon.operationsBoard.Stores.Repo;

import com.kazyon.operationsBoard.Stores.Model.Stores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface StoresRepository extends JpaRepository<Stores, Integer> {
    Optional<Stores> findByName(String name);
}