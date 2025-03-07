package org.solent.oodd.webexercise1.spring.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.solent.oodd.webexercise1.model.User;

@Controller
@RequestMapping("/")
public class MVCController {

    // this redirects calls to the root of our application to index.html 
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model) {
        return "redirect:/index.html";
    }
    
    @RequestMapping(value = "/userlist", method = {RequestMethod.GET, RequestMethod.POST})
    public String jspexample3d(@RequestParam(name = "action", required = false) String action, @RequestParam(name = "userName", required = false) String name, @RequestParam(name = "userAddress", required = false) String address, @RequestParam(name = "index", required = false) String index, Model model, HttpSession session) {
        List<User> users = (List<User>) session.getAttribute("usersList");
        if (users == null) {
            users = new ArrayList<User>();
            session.setAttribute("usersList", users);
        }

        if ("removeUser".equals(action)) {
            int i = Integer.parseInt(index);
            users.remove(i);
        } else if ("addUser".equals(action)) {
            User user = new User();
            user.setName(name);
            user.setAddress(address);
            users.add(user);
        }
        
        model.addAttribute("users", users);
        
        return "jspexample3d";
    }
    
    @RequestMapping(value = "/userlist-modify", method = {RequestMethod.GET})
    public String jspexample_modify_post(
            @RequestParam(value = "index", required = false) String index, 
            Model model, 
            HttpSession session) {
        
        List<User> users = (List<User>) session.getAttribute("usersList");
        if (users == null) {
            users = new ArrayList<User>();
            session.setAttribute("usersList", users);
        }
        
        int i = Integer.parseInt(index);
        User user = users.get(i);
        
        model.addAttribute("user", user);
        model.addAttribute("index", index);
        
        return "jspexample3d-modify";
        
    }
    
    @RequestMapping(value = "/userlist-modify", method = {RequestMethod.POST})
    public String jspexample3d_modify_get(
            @RequestParam(value = "index", required = false) String index, 
            @RequestParam(name = "userName", required = false) String name, 
            @RequestParam(name = "userAddress", required = false) String address, 
            Model model, 
            HttpSession session) {
        
        List<User> users = (List<User>) session.getAttribute("usersList");
        if (users == null) {
            users = new ArrayList<User>();
            session.setAttribute("usersList", users);
        }

        int i = Integer.parseInt(index);
        User user = users.get(i);

        user.setAddress(address);
        user.setName(name);
        
        model.addAttribute("user", user);
        model.addAttribute("index", index);
        
        return "jspexample3d-modify";
    }

    /**
     * This just throws a runtime exception to show error handler working
     */
    @RequestMapping(value = "/xxxhome", method = {RequestMethod.GET, RequestMethod.POST})
    public String xxxCart(
            Model model,
            HttpSession session) {

        if (true) {
            throw new RuntimeException("error caused by request to /xxxhome");
        }
        return "home";
    }

    /*
     * Default exception handler, catches all exceptions, redirects to friendly
     * error page. Does not catch request mapping errors
     */
    @ExceptionHandler(Exception.class)
    public String myExceptionHandler(final Exception e, Model model, HttpServletRequest request) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        final String strStackTrace = sw.toString(); // stack trace as a string
        String urlStr = "not defined";
        if (request != null) {
            StringBuffer url = request.getRequestURL();
            urlStr = url.toString();
        }
        model.addAttribute("requestUrl", urlStr);
        model.addAttribute("strStackTrace", strStackTrace);
        model.addAttribute("exception", e);
        //logger.error(strStackTrace); // send to logger first
        return "error"; // default friendly exception message for user
    }

}
