package org.controle.servicos.spring.dao;

import java.util.List;

public interface DAOBase<T> {

	public List<T> list(int offset, int max);
	
	public List<T> listAll();
	
	public void persistir(T objeto);
	
	public void excluir(T objeto);
	
	public T get(Long id);

	public List<T> list(String campoBanco, String valorCampo) throws Exception;
}
