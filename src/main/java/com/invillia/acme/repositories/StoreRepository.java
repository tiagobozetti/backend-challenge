package com.invillia.acme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invillia.acme.domain.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer>{

}
