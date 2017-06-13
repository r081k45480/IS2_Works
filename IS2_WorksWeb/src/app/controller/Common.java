package app.controller;

import javax.servlet.http.HttpServletRequest;

import model.User;

public class Common {

	
	public static User getUlogovan(HttpServletRequest r){		
		return (User) r.getSession().getAttribute("user");
	}
	
	public static void setUlogovan(HttpServletRequest r, User u){
		r.getSession().setAttribute("user", u);
	}
}
