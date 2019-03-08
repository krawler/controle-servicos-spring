package org.controle.servicos.spring.controller;

import org.controle.servicos.spring.adapter.HibernateProxyTypeAdapter;
import org.controle.servicos.spring.dao.DAOServico;
import org.controle.servicos.spring.entity.Servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RequestMapping("ws/servico")
@Controller
public class ServicoController {		
	
	@Autowired
	@Qualifier("EMServico")
	private DAOServico daoServico;
	private GsonBuilder builder;
	private  Gson gson;
		
	public ServicoController() {
		super();
		this.builder = new GsonBuilder();		
		this.builder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
		this.gson = this.builder.create();
	}

	@RequestMapping(value="listar", method=RequestMethod.GET, headers = "Accept=application/json") 
	@ResponseBody
	public String listar(){				
		return this.gson.toJson(daoServico.listAll());		
	}
	
	@RequestMapping(value="salvar", method=RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Servico> salvar(@RequestBody String strServico){
		Servico servico = new Gson().fromJson(strServico, Servico.class);
		daoServico.persistir(servico);
		return new ResponseEntity<Servico>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="get/{id}", headers = "Accept=application/json")
	@ResponseBody
	public String get(@PathVariable("id") Long id){
		Servico servico = daoServico.get(id);		
		return this.gson.toJson(servico);
	}
	
	@RequestMapping(value="/excluir/{id}")
	public String excluir(@PathVariable("id") Long id){
		//Servico servico = daoServico.get(id);
		//daoServico.excluir(servico);
		return "redirect:listar";
	}
}
