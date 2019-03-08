package org.controle.servicos.spring.dao.impl;

import org.controle.servicos.spring.dao.DAOOrdemServico;
import org.controle.servicos.spring.entity.OrdemServico;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;

@Primary
@Service("EMOrdemServico")
public class EMOrdemServico extends EMDAO<OrdemServico> implements DAOOrdemServico {

	@Override
	protected Class getClazz() {
		return OrdemServico.class;
	}

}
