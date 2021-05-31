package com.sandipan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sandipan.service.DemoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DemoController {

	// == Fields ==
	private final DemoService demoService;

	// == Public Constructors
	@Autowired
	public DemoController(DemoService demoService) {
		this.demoService = demoService;
	}

	// == Request Methods
	// http://localhost:8080/todo-list/hello
	// this URL will invoke this method
	@ResponseBody
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	// http://localhost:8080/todo-list/welcome
	@GetMapping("welcome")
	public String welcome(Model model) {
		/*
		 * prefix + name + suffix WEB-INF/view/welcome.jsp we specified the view
		 * location in suffix and extension in prefix in ViewResolver configuration in
		 * WebConfig class. here return welcome represent the name of the view. the
		 * suffix and prefix will be added by view resolver so we don't need to specify
		 * here. actually the view will be looking like this WEB-INF/view/welcome.jsp
		 * Model is interface which will be created by dispatcher Servlet, which will be
		 * passed as parameter in the controller.We can view the model as key value pair
		 * or a map.It is created by dispatcher servlet before invoking the handler
		 * method. We can add attributes to model which will be then available in view
		 * as data. Data will be passed from the controller to view and view to
		 * controller
		 */
		model.addAttribute("user", "Sandipan");
		// remember the key value in controller and placeholder value in jsp should be
		// same.
		log.info("model= {}", model);
		return "welcome";
	}

	/*using service class methods
	http://localhost:8080/todo-list/welcome2
	query parameter which is basically request parameter
	http://localhost:8080/todo-list/welcome2?user=Sandipan
	to use multiple parameter we have have to separate them using '&'
	http://localhost:8080/todo-list/welcome2?user=Sandipan&age=45
	*/
	@GetMapping("welcome2")
	public String newWelcome(@RequestParam String user, @RequestParam int age ,Model model) {
		log.info("model= {}", model);
		model.addAttribute("helloMessage", demoService.getHelloMessage(user));
		model.addAttribute("age", age);
		return "newWelcome";
	}
	/*we can change the browser parameter name using @RequestParam("newNAme")
	the parameter should be present in browser.
	we are showing the view based on the parameter
	the value of user parameter is passing from the browser
	*/
	
	
	// == Model Attributes ==
	@ModelAttribute("welcomeMessage")
	 /*model using annotation.It will automatically add attribute to model and fetch
	 attribute from model.
	 we are specifying the name of the attribute which will be added to our jsp
	 page.
	 if attribute name is not specified then the attribute generates a name from
	 the return value starting with lower case.
	 */	
	public String welcomeMessage() {
		log.info("welcomeMessage() called");
		return "welcome to this demo application";
	}
	
	//with service class(layer)
	@ModelAttribute("newWelcomeMessage")
	public String newWelcomeMessage() {
		log.info("welcomeMessage() called");
		return demoService.getWelcomeMessage();
	}

}
