package com.epam.jewelry_shop.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface CRUDService<E, K> {

	boolean save(E entity) throws NoSuchAlgorithmException, InvalidKeySpecException;

}