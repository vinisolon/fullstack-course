package com.vinisolon.fullstackcourse;

import com.vinisolon.fullstackcourse.domain.Categoria;
import com.vinisolon.fullstackcourse.domain.Cidade;
import com.vinisolon.fullstackcourse.domain.Estado;
import com.vinisolon.fullstackcourse.domain.Produto;
import com.vinisolon.fullstackcourse.repositories.CategoriaRepository;
import com.vinisolon.fullstackcourse.repositories.CidadeRepository;
import com.vinisolon.fullstackcourse.repositories.EstadoRepository;
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
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;

	@Override
	public void run(String... args) throws Exception {
		// Produtos e categorias
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

		// Estados e cidades
		Estado sp = new Estado(null, "São Paulo", "SP");
		Estado mg = new Estado(null, "Minas Gerais", "MG");

		Cidade campinas = new Cidade(null, "Campinas", sp);
		Cidade saoPaulo = new Cidade(null, "São Paulo", sp);
		Cidade uberlandia = new Cidade(null, "Uberlândia", mg);

		sp.getCidades().addAll(Arrays.asList(campinas, saoPaulo));
		mg.getCidades().add(uberlandia);

		estadoRepository.saveAll(Arrays.asList(sp, mg));
		cidadeRepository.saveAll(Arrays.asList(campinas, saoPaulo, uberlandia));
	}
}
