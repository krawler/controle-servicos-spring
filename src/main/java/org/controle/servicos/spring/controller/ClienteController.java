package org.controle.servicos.spring.controller;

import org.controle.servicos.spring.dao.DAOCliente;
import org.controle.servicos.spring.entity.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RequestMapping("ws/cliente")
@Controller
public class ClienteController {
	
	@Autowired
	@Qualifier("EMCliente")
	private DAOCliente daoCliente;
	
	@RequestMapping(value="listar", method=RequestMethod.GET, headers="Accept=application/json")
	@ResponseBody
	public String listar() {
		return new Gson().toJson(daoCliente.listAll());
	}
	
	@RequestMapping(value="salvar", method=RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Cliente> salvar(@RequestBody String strCliente){		
		
		Cliente cliente = new GsonBuilder().setDateFormat("yyyy-dd-mm").create()
						  .fromJson(strCliente, Cliente.class);
		daoCliente.persistir(cliente);		
		return new ResponseEntity<Cliente>(HttpStatus.CREATED);		
	}	

}
