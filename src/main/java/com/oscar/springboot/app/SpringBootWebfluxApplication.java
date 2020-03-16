package com.oscar.springboot.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.oscar.springboot.app.models.dao.ProductoDao;
import com.oscar.springboot.app.models.documents.Producto;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootWebfluxApplication implements CommandLineRunner{

	private static final Logger log = LoggerFactory.getLogger(SpringBootWebfluxApplication.class);
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	
	@Autowired
	ProductoDao productoDao;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		mongoTemplate.dropCollection("productos").subscribe();
		
		Flux.just(new Producto("Tv pantalla", 453.12),
				new Producto("Camara Sony", 123.2))
		.flatMap(producto -> productoDao.save(producto))
		.subscribe(producto -> log.info("insert: " + producto.getId())) ;
	}

}
