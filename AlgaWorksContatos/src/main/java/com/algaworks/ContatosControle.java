package com.algaworks;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContatosControle {
	
	private static final ArrayList<Contato> LISTA_CONTATOS = new ArrayList<Contato>(); //Arraylist, ele é dinâmico.
	
	static {
		
	LISTA_CONTATOS.add(new Contato("1", "Maria", "+55 14 123456789"));
	LISTA_CONTATOS.add(new Contato("2", "João", "+55 11 415645879"));
	LISTA_CONTATOS.add(new Contato("3", "Normandes", "+55 47 021365798"));
	LISTA_CONTATOS.add(new Contato("4", "Thiago", "+55 31 987453652"));
	LISTA_CONTATOS.add(new Contato("5", "Alexandre", "+55 18 798503216"));
	
	}
	
	//private static final Contatos[] LISTA_CONTATOS_2 = new Contatos[2];//Array, tem que informar o tamanho do array.
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/contatos")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("listar");
		
		modelAndView.addObject("contatos", LISTA_CONTATOS);
		return modelAndView;
	}
	
	@GetMapping("/contatos/novo")
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView("formulario");
		modelAndView.addObject("contato", new Contato());
		return modelAndView;
	}
	
	
	@PostMapping("/contatos")
	public String cadastrar(Contato contato, RedirectAttributes attributes) {
		
		String id = UUID.randomUUID().toString();//Método do Java que gera um ID.
		
		contato.setId(id);
		
		LISTA_CONTATOS.add(contato);

		attributes.addFlashAttribute("mensagem", "Cadastrado com sucesso!");

		
		return "redirect:/contatos";
	}
	
	
	@GetMapping("/contatos/{id}/editar")
	public ModelAndView editar(@PathVariable String id) {//O @PathVariable diz para o Spring MVC que o parâmetro String id está no 
														 //path @GetMapping("/contatos/{id}/editar") 
		ModelAndView modelAndView = new ModelAndView("formulario");
		
		Contato contato = procurarContato(id);
		
		modelAndView.addObject("contato", contato);
		return modelAndView;
		
	}
	
	
	@PutMapping ("/contatos/{id}")
	public String atualizar (Contato contato, RedirectAttributes attributes) {
		Integer indice = procurarIndiceContato(contato.getId());
			
		Contato contatoVelho = LISTA_CONTATOS.get(indice);
		
		LISTA_CONTATOS.remove(contatoVelho);
		
		LISTA_CONTATOS.add(indice, contato); //Pega o indice do contato novo e o contato atualizado e salva
		
		attributes.addFlashAttribute("mensagem", "Atualizado com sucesso!");
		
		return "redirect:/contatos";	
		
	}
	
	
	
	@DeleteMapping("/contatos/{id}")
	public String remover(@PathVariable String id, RedirectAttributes attributes) {
		Contato contato = procurarContato(id);
		
		LISTA_CONTATOS.remove(contato);
		
		attributes.addFlashAttribute("mensagem", "Excluido com sucesso!");
		return "redirect:/contatos";
	}
	
	
	//----------------------------------- MÉTODOS AUXILIÁRIRES (Não respondem requisições do browser) 
	
	private Contato procurarContato (String id) {
		
		Integer indice = procurarIndiceContato(id);
		if (indice != null) {
			Contato contato = LISTA_CONTATOS.get(indice);
			return contato;
		}
			return null;	
	}
	
	private Integer procurarIndiceContato (String id) {
		for (int i = 0; i < LISTA_CONTATOS.size(); i++) {
			Contato contato = LISTA_CONTATOS.get(i);
			
			if (contato.getId().equals(id)) {
				return i;	
			}
		}
		return null;
	}
	
}













