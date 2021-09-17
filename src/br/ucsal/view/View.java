package br.ucsal.view;

import java.util.ArrayList;
import java.util.Scanner;

import br.ucsal.dao.Dao;
import br.ucsal.model.Tarefa;

public class View {
	
	Dao<Tarefa> dao;
	
	public Integer mostrarMenu() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Olá, escolha uma das opções abaixo: \n"
							+ "1 para retornar todas as tarefas salvas na lista; \n"
							+ "2 para salvar uma nova atividade na lista; \n"
							+ "3 para editar uma atividade da lista \n"
							+ "4 para buscar uma atividade pelo título \n"
							+ "5 para deletar uma atividade da lista; \n");
		try {
			return validarRespostaUsuario(scanner.nextInt());
		} catch (Exception e) {
			System.out.print("\n Digite apenas números! Tente novamente. \n" + e.getStackTrace());
			return null;
		}
		
	}
	
	public int validarRespostaUsuario(int resposta){
		if(resposta < 1 || resposta > 5) {
			System.out.print("\n Opção inválida! Tente novamente. \n");
			mostrarMenu();
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
				
				System.out.print("\n -------------------------------------- \n");
				
				System.out.printf("Título: %s \n", tarefa.getTitulo());
				System.out.printf("Descrição: %s \n", tarefa.getDescricao());
				System.out.printf("Situação: %s \n", tarefa.getSituacao());
				
				System.out.print("\n -------------------------------------- \n");
				
			}
		}
		
	}

//	System.out.printf("\n %s salva com sucesso! \n", tarefa.getTitulo()); 
//	System.out.printf("\n %s substituida por %s com sucesso! \n", tarefaAntiga.getTitulo(), tarefaNova.getTitulo());
//	System.out.printf("\n %s não encontrada! \n", nomeDaTarefaAntiga);
//	System.out.printf("\n %s deletada com sucesso! \n", nomeDaTarefa); 
//	System.out.printf("\n %s não encontrada! \n", nomeDaTarefa);
}
