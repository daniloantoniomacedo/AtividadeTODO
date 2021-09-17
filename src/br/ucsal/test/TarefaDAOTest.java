package br.ucsal.test;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.ucsal.dao.Dao;
import br.ucsal.model.Situacao;
import br.ucsal.model.Tarefa;

class TarefaDAOTest {

	private Tarefa tarefa01 = new Tarefa("tarefa01", "Primeira tarefa", Situacao.CONCLUIDA);
	private Tarefa tarefa02 = new Tarefa("tarefa02", "Segunda tarefa", Situacao.EM_ANDAMENTO);
	private ArrayList<Tarefa> listaDeTestes = new ArrayList<Tarefa>(Arrays.asList(tarefa01));
	private Dao<Tarefa> dao = new Dao<Tarefa>();
	
	@BeforeEach
	void setUpBeforeClass() {
		this.dao.listaDeTarefas.add(tarefa01);
	}

	@Test
	void testInserirTarefaRetornaTarefa01() {
		Assert.assertEquals(tarefa01, dao.inserirTarefa(tarefa01));
	}

	@Test
	void testListarTarefasRetornaListaDeTestes() {
		Assert.assertEquals(listaDeTestes, dao.listarTarefas());
	}

	@Test
	void testBuscarTarefaPorTituloRetornaTarefa01() {
		Assert.assertEquals(tarefa01, dao.buscarTarefaPorTitulo("tarefa01"));
	}

	@Test
	void testAlterarTarefaPeloNomeRetornaTarefa02() {
		Assert.assertEquals(tarefa02, dao.alterarTarefaPeloTitulo("tarefa01", tarefa02));
	}

	@Test
	void testDeletarTarefaPeloNomeRetornaTarefa01() {
		Assert.assertEquals(tarefa01, dao.deletarTarefaPeloTitulo("tarefa01"));
	}

}
