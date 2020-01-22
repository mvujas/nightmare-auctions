package com.github.mvujas.nightmareauctionsbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.github.mvujas.nightmareauctionsbackend.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item> {

}
