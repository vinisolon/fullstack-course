package com.vinisolon.fullstackcourse;

import com.vinisolon.fullstackcourse.domain.*;
import com.vinisolon.fullstackcourse.domain.enums.TipoCliente;
import com.vinisolon.fullstackcourse.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

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
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public void run(String... args) throws Exception {
		// Produtos e categorias
		Categoria categoriaInformatica = new Categoria(null, "Informática");
		Categoria categoriaEscritorio = new Categoria(null, "Escritório");

		Produto produtoComputador = new Produto(null, "Computador", 2000.00);
		Produto produtoImpressora = new Produto(null, "Impressora", 800.00);
		Produto produtoMouse = new Produto(null, "Mouse", 80.00);

		categoriaInformatica.getProdutos().addAll(Arrays.asList(produtoComputador, produtoImpressora, produtoMouse));
		categoriaEscritorio.getProdutos().add(produtoImpressora);

		produtoComputador.getCategorias().add(categoriaInformatica);
		produtoImpressora.getCategorias().addAll(Arrays.asList(categoriaInformatica, categoriaEscritorio));
		produtoMouse.getCategorias().add(categoriaInformatica);

		categoriaRepository.saveAll(Arrays.asList(categoriaInformatica, categoriaEscritorio));
		produtoRepository.saveAll(Arrays.asList(produtoComputador, produtoImpressora, produtoMouse));

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

		// Clientes e endereços
		Cliente clienteMaria = new Cliente(null, "Maria Silva", "maria@gmail.com",
				"36378912377", TipoCliente.PESSOA_FISICA);

		clienteMaria.getTelefone().addAll(Arrays.asList("27363323", "93838393"));

		Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "Apto 303",
				"Jardim", "38220-834", clienteMaria, campinas);
		Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "Sala 800",
				"Centro", "38777-012", clienteMaria, saoPaulo);

		clienteMaria.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));

		clienteRepository.saveAll(List.of(clienteMaria));
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
	}
}
