package br.ucsal.view;

import java.util.ArrayList;
import java.util.Scanner;

import br.ucsal.dao.Dao;
import br.ucsal.model.Situacao;
import br.ucsal.model.Tarefa;

public class View {
	
	Dao<Tarefa> dao;
	Scanner scanner;
		
	public View(Dao<Tarefa> dao, Scanner scanner) {
		this.dao = dao;
		this.scanner = scanner;
	}

	public Integer escolherOpcaoMenu() {
		System.out.print("Olá, escolha uma das opções abaixo: \n"
							+ "1 para retornar todas as tarefas salvas na lista; \n"
							+ "2 para salvar uma nova tarefa na lista; \n"
							+ "3 para editar uma tarefa da lista; \n"
							+ "4 para buscar uma tarefa pelo título; \n"
							+ "5 para deletar uma tarefas da lista; \n");
		try {
			return validarRespostaUsuario(scanner.nextInt());
		} catch (Exception e) {
			System.out.print("\n Digite apenas números! Programa Encerrado! \n");
			return null;
		}
		
	}
	
	public int validarRespostaUsuario(int resposta){
		if(resposta < 1 || resposta > 5) {
			System.out.print("\n Opção inválida! Tente novamente. \n");
			escolherOpcaoMenu();
		}
		return resposta;
	}
	
	public void imprimirListaFormatada() {
		ArrayList<Tarefa> lista = dao.listarTarefas();
		
		if(lista.isEmpty()) {
			System.out.println("A lista está vazia! Adicione um item.");
		}
		
		if(!lista.isEmpty()) {
			for (Tarefa tarefa : lista) {
				imprimirTarefaFormatada(tarefa);
			}
		}
	}
	
	public void imprimirTarefaFormatada(Tarefa tarefa) {
		System.out.print("-------------------------------------- \n");
		
		System.out.printf("Título: %s \n", tarefa.getTitulo());
		System.out.printf("Descrição: %s \n", tarefa.getDescricao());
		System.out.printf("Situação: %s", tarefa.getSituacao());
		
		System.out.print("\n--------------------------------------");
	}
	
	public void criarNovoTitulo(Tarefa tarefa) {
		scanner.nextLine();
		System.out.println("Qual título da tarefa?");
		String titulo = scanner.nextLine();
		tarefa.setTitulo(titulo);
	}
	
	public void criarNovaDescricao(Tarefa tarefa) {
		System.out.println("Qual a descrição da tarefa?");
		String descricao = scanner.nextLine();
		tarefa.setDescricao(descricao);
	}
	
	public void escolherNovaSituacao(Tarefa tarefa) {
		System.out.println("Qual o estado atual da tarefa? \n"
				+ "Digite 1 para: em andamento; \n"
				+ "Digite 2 para: concluída.");
		Integer opcao = scanner.nextInt();
		if(opcao != 1 && opcao != 2) {
			System.out.println("Opção inválida. Tente novamente.");
			criarNovaTarefa(tarefa);
		}else {
			if(opcao == 1) tarefa.setSituacao(Situacao.EM_ANDAMENTO);
			if(opcao == 2) tarefa.setSituacao(Situacao.CONCLUIDA);
		}
	}
	
	public void criarNovaTarefa(Tarefa tarefa) {
		criarNovoTitulo(tarefa);
		criarNovaDescricao(tarefa);
		escolherNovaSituacao(tarefa);
		
		this.dao.inserirTarefa(tarefa);
		System.out.printf("\n %s salva com sucesso! \n", tarefa.getTitulo()); 

	}
	
	public void buscarTarefa() {
		scanner.nextLine();
		System.out.println("Qual o título da tarefa a ser buscada?");
		String busca = scanner.nextLine();
		Tarefa tarefaBusca = this.dao.buscarTarefaPorTitulo(busca);
		if(tarefaBusca != null) imprimirTarefaFormatada(tarefaBusca);
		if(tarefaBusca == null) System.out.printf("\n %s não encontrada! \n", busca); 
	}
	
	public void editarTarefa() {
		scanner.nextLine();
		System.out.println("Qual o título da tarefa a ser editada?");
		String busca = scanner.nextLine();
		
		System.out.println("Tarefa a ser editada:");
		Tarefa tarefa = this.dao.buscarTarefaPorTitulo(busca);
		
		if (tarefa == null) System.out.println("Tarefa não encontrada!");
		
		if (tarefa != null) {
		imprimirTarefaFormatada(tarefa);
		String tituloAntigo = tarefa.getTitulo();
		System.out.println("\nEdição da tarefa:");
		System.out.println("Qual é novo título da tarefa?");
		tarefa.setTitulo(scanner.nextLine());
		System.out.println("Qual é nova descrição da tarefa?");
		tarefa.setDescricao(scanner.nextLine());
		escolherNovaSituacao(tarefa);
		this.dao.alterarTarefaPeloTitulo(busca, tarefa);
		System.out.printf("\n %s substituida por %s com sucesso! \n", tituloAntigo, tarefa.getTitulo());
		}
	}
	
	public void deletarTarefa() {
		scanner.nextLine();
		System.out.println("Qual o título da tarefa a ser deletada?");
		String busca = scanner.nextLine();
		System.out.println("Tarefa a ser deletada:");
		Tarefa tarefaDeletar = this.dao.buscarTarefaPorTitulo(busca);
		if (tarefaDeletar == null) System.out.println("Tarefa não encontrada!");
		if (tarefaDeletar != null) {
		this.dao.deletarTarefaPeloTitulo(busca);
		System.out.printf("\n%s deletada com sucesso! \n", busca); 
		}
	}
	
}
