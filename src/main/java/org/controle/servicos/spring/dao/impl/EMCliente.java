package org.controle.servicos.spring.dao.impl;

import org.controle.servicos.spring.dao.DAOCliente;
import org.controle.servicos.spring.entity.Cliente;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("EMCliente")
public class EMCliente extends EMDAO<Cliente> implements DAOCliente {

	@Override
	protected Class getClazz() {
		return Cliente.class;
	}	
}
