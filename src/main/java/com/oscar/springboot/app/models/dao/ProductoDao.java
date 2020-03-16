package com.oscar.springboot.app.models.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.oscar.springboot.app.models.documents.Producto;

public interface ProductoDao extends ReactiveMongoRepository<Producto, String>{

}
