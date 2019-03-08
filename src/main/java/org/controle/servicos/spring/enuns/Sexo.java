package org.controle.servicos.spring.enuns;

import java.util.HashMap;
import java.util.Map;

public enum Sexo {
	
	MASCULINO(1), FEMININO(0);
	
	private int value;
	private static Map<Integer, Sexo> map = new HashMap<Integer, Sexo>();

	private Sexo(int value) {		
		this.value = value;
	}	
	
	static {
		for (Sexo sexo : Sexo.values()) {
			 map.put(sexo.value, sexo);
		}
	}
	
	public static Sexo valueOf(int value) {
		return (Sexo) map.get(value); 
	}

	public int getValue() {
		return value;
	}	
	
}
