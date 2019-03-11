package org.controle.servicos.spring.controller;

import org.controle.servicos.spring.dao.DAOOrdemServico;
import org.controle.servicos.spring.entity.OrdemServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.GsonBuilder;

@RequestMapping("ws/ordem")
@Controller
public class OrdemServicoController {
		
	@Autowired
	private DAOOrdemServico daoOrdemServico;
	
	@RequestMapping(value="salvar", method=RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<OrdemServico> salvar(@RequestBody String strOrdemServico){
		
		OrdemServico ordem = new GsonBuilder().setDateFormat("yyyy-mm-dd").create().fromJson(strOrdemServico, OrdemServico.class);
		daoOrdemServico.persistir(ordem);
		return new ResponseEntity<OrdemServico>(HttpStatus.CREATED);
	}
}
