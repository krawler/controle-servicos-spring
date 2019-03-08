package org.controle.servicos.spring.entity;

import java.util.HashMap;
import java.util.Map;

public enum Situacao {
	
	ABERTO(0), EM_ANDAMENTO(1), FECHADO(2);
	
	private int value;
	private static Map<Integer, Situacao> map = new HashMap<Integer, Situacao>();

	private Situacao(int value) {		
		this.value = value;
	}	
	
	static {
		for (Situacao situacao : Situacao.values()) {
			 map.put(situacao.value, situacao);
		}
	}
	
	public static Situacao valueOf(int value) {
		return (Situacao) map.get(value); 
	}

	public int getValue() {
		return value;
	}
	
}
