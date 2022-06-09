package com.vinisolon.fullstackcourse;

import com.vinisolon.fullstackcourse.domain.*;
import com.vinisolon.fullstackcourse.domain.enums.EstadoPagamento;
import com.vinisolon.fullstackcourse.domain.enums.TipoCliente;
import com.vinisolon.fullstackcourse.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Override
	public void run(String... args) throws Exception {
		// Produtos e categorias
		Categoria categoriaInformatica = new Categoria(null, "Informática");
		Categoria categoriaEscritorio = new Categoria(null, "Escritório");
		Categoria categoriaHigiene = new Categoria(null, "Higiene");
		Categoria categoriaLimpeza = new Categoria(null, "Limpeza");
		Categoria categoriaCozinha = new Categoria(null, "Cozinha");
		Categoria categoriaAutomoveis = new Categoria(null, "Automoveis");
		Categoria categoriaJardim = new Categoria(null, "Jardim");
		Categoria categoriaJogos = new Categoria(null, "Jogos");

		Produto produto1 = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impressora", 800.00);
		Produto produto3 = new Produto(null, "Mouse", 80.00);

		categoriaInformatica.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
		categoriaEscritorio.getProdutos().add(produto2);

		produto1.getCategorias().add(categoriaInformatica);
		produto2.getCategorias().addAll(Arrays.asList(categoriaInformatica, categoriaEscritorio));
		produto3.getCategorias().add(categoriaInformatica);

		categoriaRepository.saveAll(Arrays.asList(categoriaInformatica, categoriaEscritorio, categoriaHigiene,
				categoriaLimpeza, categoriaCozinha, categoriaAutomoveis, categoriaJardim, categoriaJogos));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));

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

		clienteMaria.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "Apto 303",
				"Jardim", "38220-834", clienteMaria, campinas);
		Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "Sala 800",
				"Centro", "38777-012", clienteMaria, saoPaulo);

		clienteMaria.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));

		clienteRepository.saveAll(List.of(clienteMaria));
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));

		// Pedidos e pagamentos
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido pedido1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), clienteMaria, endereco1);
		Pedido pedido2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), clienteMaria, endereco2);

		Pagamento pagamento1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		pedido1.setPagamento(pagamento1);

		Pagamento pagamento2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, pedido2, sdf.parse("20/10/2017 00:00"), null);
		pedido2.setPagamento(pagamento2);

		clienteMaria.getPedidos().addAll(Arrays.asList(pedido1, pedido2));

		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));

		// ItemPedido
		ItemPedido itemPedido1 = new ItemPedido(pedido1, produto1, 1, 2000., 0.);
		ItemPedido itemPedido2 = new ItemPedido(pedido1, produto3, 2, 80., 0.);
		ItemPedido itemPedido3 = new ItemPedido(pedido2, produto2, 1, 800., 100.0);

		pedido1.getItens().addAll(Arrays.asList(itemPedido1, itemPedido2));
		pedido2.getItens().add(itemPedido3);

		produto1.getItens().add(itemPedido1);
		produto2.getItens().add(itemPedido3);
		produto3.getItens().add(itemPedido2);

		itemPedidoRepository.saveAll(Arrays.asList(itemPedido1, itemPedido2, itemPedido3));
	}
}
