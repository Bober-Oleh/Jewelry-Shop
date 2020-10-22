package com.epam.jewelry_shop.repository;

import java.util.List;

public interface CRUDRepository<E, K> {

	List<E> findAll();

}
