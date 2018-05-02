package com.fonseca.springmodconc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fonseca.springmodconc.domain.Categoria;
import com.fonseca.springmodconc.domain.Cidade;
import com.fonseca.springmodconc.domain.Cliente;
import com.fonseca.springmodconc.domain.Endereco;
import com.fonseca.springmodconc.domain.Estado;
import com.fonseca.springmodconc.domain.ItemPedido;
import com.fonseca.springmodconc.domain.Pagamento;
import com.fonseca.springmodconc.domain.PagamentoComBoleto;
import com.fonseca.springmodconc.domain.PagamentoComCartao;
import com.fonseca.springmodconc.domain.Pedido;
import com.fonseca.springmodconc.domain.Produto;
import com.fonseca.springmodconc.domain.enums.EstadoPagamento;
import com.fonseca.springmodconc.domain.enums.TipoCliente;
import com.fonseca.springmodconc.repositories.CategoriaRepository;
import com.fonseca.springmodconc.repositories.CidadeRepository;
import com.fonseca.springmodconc.repositories.ClienteRepository;
import com.fonseca.springmodconc.repositories.EnderecoRepository;
import com.fonseca.springmodconc.repositories.EstadoRepository;
import com.fonseca.springmodconc.repositories.ItemPedidoRepository;
import com.fonseca.springmodconc.repositories.PagamentoRepository;
import com.fonseca.springmodconc.repositories.PedidoRepository;
import com.fonseca.springmodconc.repositories.ProdutoRepository;

@SpringBootApplication
public class SpringModConcApplication implements CommandLineRunner {
	
	@Autowired 
	private CategoriaRepository categoriaRepository;
	@Autowired 
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
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
 
	public static void main(String[] args) {
		SpringApplication.run(SpringModConcApplication.class, args);
	}
 
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.save(Arrays.asList(cat1, cat2));  
		produtoRepository.save(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "Sâo Paulo", est2);
		Cidade c3 = new Cidade(null, "CAmpinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));		
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "12312312312", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("45456565", "989745455"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Ap 33", "Jardim", "05033001", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Pequis", "26", "Ap 22", "Vila", "05144000", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2); 
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));
	}
} 