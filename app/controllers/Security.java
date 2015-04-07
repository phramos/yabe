package controllers;

import models.User;
import play.mvc.*;

public class Security extends Secure.Security {

	static boolean authenticate(String username, String password) {
	    
		return User.connect(username, password) != null;
	    
	}
	
	static void onAuthenticated() {
	    Admin.index();
	}
	
	static void onDisconnected() {
	    Admin.index();
	}
	
	static boolean check(String profile) {
	    if("admin".equals(profile)) {
	        return User.find("byEmail", connected()).<User>first().isAdmin;
	    }
	    return false;
	}
}
