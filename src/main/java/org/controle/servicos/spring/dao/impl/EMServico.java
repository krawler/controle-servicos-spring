package org.controle.servicos.spring.dao.impl;

import org.controle.servicos.spring.dao.DAOServico;
import org.controle.servicos.spring.entity.Servico;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service("EMServico")
public class EMServico extends EMDAO<Servico> implements DAOServico {

	@Override
	protected Class getClazz() {
		return Servico.class;
	}

}
