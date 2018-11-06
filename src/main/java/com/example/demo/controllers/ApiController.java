package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;

@Controller
public class ApiController {

  
  //https://developers.facebook.com/apps/
  //Url de config
	@RequestMapping("/")
	public ModelAndView view() throws FacebookException  {
		
		Facebook facebook = new FacebookFactory().getInstance();
		
		String appId = "368382733702235";
		String appSecret = "";
		String permissions = "user_posts, user_status, publish_pages, publish_to_groups";
		String token = "CHAVE";
		
		
		facebook.setOAuthAppId(appId, appSecret);
		facebook.setOAuthPermissions(permissions);
		facebook.setOAuthAccessToken(new AccessToken(token) );
		
		String nome = facebook.getName();
		String id = facebook.getId();
		
		ModelAndView modelAndView = new ModelAndView("/home");
		 modelAndView.addObject("nome", nome);
		 modelAndView.addObject("id", id);
		 
		 ResponseList<Post> posts = facebook.getFeed(id);
		 
		
		 String[] postagens = new String[200];
		 int cont = 0;
		 for (Post post : posts) {
			 postagens[cont] = post.getMessage();
			 
			cont++;
			 
		 }
	
		modelAndView.addObject("post", postagens);
		 
		 return modelAndView;
		 
		
		
	}
	
	@RequestMapping("/home")
	public String home() {
	  return "home";
	}
}
