package com.vinisolon.fullstackcourse;

import com.vinisolon.fullstackcourse.domain.Categoria;
import com.vinisolon.fullstackcourse.domain.Produto;
import com.vinisolon.fullstackcourse.repositories.CategoriaRepository;
import com.vinisolon.fullstackcourse.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class FullstackCourseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FullstackCourseApplication.class, args);
	}

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().add(p2);

		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
	}
}
