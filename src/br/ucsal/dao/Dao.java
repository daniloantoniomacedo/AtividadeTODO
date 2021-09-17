package br.ucsal.dao;

import java.util.ArrayList;

import br.ucsal.model.Tarefa;

public class Dao<T> {
	
	public ArrayList<T> listaDeTarefas = new ArrayList<T>();
	
	/**
	 * Insere a tarefa passada como parametro na lista.
	 * @param tarefa
	 * @return tarefa inserida
	 */
	public T inserirTarefa(T tarefa) { 
		this.listaDeTarefas.add(tarefa); 
		return tarefa;
	}
	
	/**
	 * Lista todas as tarefas da lista.
	 * @return Lista de tarefas.
	 */
	public ArrayList<T> listarTarefas() { return this.listaDeTarefas; }
	
	/**
	 * Busca tarefa pelo titulo na lista. Caso a tarefa não seja encontrada, retorna null.
	 * @param buscaTitulo
	 * @return Tarefa encontrada ou null
	 */
	public T buscarTarefaPorTitulo(String buscaTitulo){ 
		
		for(T tarefa : listaDeTarefas) {
			if(((Tarefa) tarefa).getTitulo().equals(buscaTitulo)) {
				return tarefa;
			}
		}
		return null;
		//return (Tarefa) this.listaDeTarefas.stream().filter(tarefa -> tarefa.getTitulo().equals(buscaTitulo)); 
	}
	
	/**
	 * Busca a tarefa pelo nome na lista e altera seu conteúdo. 
	 * @param nomeDaTarefaAntiga nome da tarefa antiga.
	 * @param tarefaNova tarefa nova a substituir uma tarefa já existente na lista.
	 * @return Tarefa tarefa nova a ser inserida ou null caso a alteração não for bem sucedida. 
	 */
	public T alterarTarefaPeloTitulo(String nomeDaTarefaAntiga,T tarefaNova) {
		
		T tarefaAntiga = buscarTarefaPorTitulo(nomeDaTarefaAntiga);
		
		if(tarefaAntiga != null) {
			this.listaDeTarefas.set(listaDeTarefas.indexOf(tarefaAntiga), tarefaNova); 
			return tarefaNova;
		}else{
			return null;
		}
		
	}
	
	/**
	 * Deleta uma tarefa na lista.
	 * @param nomeDaTarefa nome da tarefa a ser deletada. 
	 * @return tarefa deletada ou null caso a deleção não seja bem sucedida.
	 */
	public T deletarTarefaPeloTitulo(String nomeDaTarefa) {
		
		T tarefaDeletar = buscarTarefaPorTitulo(nomeDaTarefa);
		
		try {
			this.listaDeTarefas.remove(listaDeTarefas.indexOf(tarefaDeletar));
			return tarefaDeletar;
		} catch (Exception e) {
			return null;
		}
		
	}
	

}