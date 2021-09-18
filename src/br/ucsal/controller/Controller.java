package br.ucsal.controller;

import java.util.Scanner;

import br.ucsal.dao.Dao;
import br.ucsal.model.Situacao;
import br.ucsal.model.Tarefa;
import br.ucsal.view.View;

public class Controller {

	public static void main(String[] args) {
		
		Dao<Tarefa> dao = new Dao<Tarefa>();
		dao.listaDeTarefas.add(new Tarefa("tarefa01", "Primeira tarefa", Situacao.CONCLUIDA));
		Scanner scanner =  new Scanner(System.in);
		View view = new View(dao, scanner);
		Controller controller = new Controller();
		
		controller.iniciar(view, scanner);
						
	}
	
	public void iniciar(View view, Scanner scanner) {
		Integer opcao = view.escolherOpcaoMenu();
		
		switch (opcao) {
		case 1: view.imprimirListaFormatada(); break;
		case 2: 
			Tarefa tarefa = new Tarefa(null, null, null);
			view.criarNovaTarefa(tarefa); 
			break;
		case 3: view.editarTarefa(); break;
		case 4: view.buscarTarefa(); break;
		case 5: view.deletarTarefa(); break;
		default: 
			System.out.println("Programa encerrado!");
			System.exit(0);
		}
		retornar(view, scanner);
	}
	
	public void retornar(View view, Scanner scanner) {
		System.out.println("\nDeseja retornar ao menu principal? \n"
				+ "Digite 1 para sim; \n"
				+ "Digite qualquer numero diferente de 1 para encerrar o programa.");
		int opcao = scanner.nextInt();
		switch (opcao) {
		case 1: iniciar(view, scanner); break;
		default: 
			System.out.println("Programa encerrado!");
			System.exit(0);
		}
	}

}
