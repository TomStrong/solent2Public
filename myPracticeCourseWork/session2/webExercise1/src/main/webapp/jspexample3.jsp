<%-- 
    Document   : jspexample3
    Created on : 22-Jul-2021, 11:16:28
    Author     : admin
    THIS EXAMPLE SHOWS HOW OBJECTS CAN BE STORED IN THE SESSION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.solent.oodd.webexercise1.model.User" %>
<%
    // retrieve the stored users list from the session
    List<User> users = (List<User>) session.getAttribute("users");
    if (users == null) {
        users = new ArrayList<>();
        session.setAttribute("users", users);
    }

    String name = request.getParameter("userName");
    String email = request.getParameter("email");
    int userID = Integer.parseInt(request.getParameter("userID"));

    // find what action to perform on the page
    String action = request.getParameter("action");
    
    User newUser = new User();
    newUser.setUsername(name);
    newUser.setEmail(email);

    if ("removeUser".equals(action)) {
        users.remove(name);
    } else if ("addUser".equals(action)) {
        users.add(newUser);
    } else if ("editUser".equals(action)) {
        users.get(userID);
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Example 3</title>
    </head>
    <body>
        <h1>JSP Example 3: User list</h1>

        <h2>user list</h2>
        
        <table>
            <% for (int idx = 0; idx < users.size(); idx++) {
                    User user = users.get(idx);
            %>
            <tr>
                <td><p><%=idx + 1%></p></td>
                <td><p><%=user.getUsername()%></p></td>
                <td><p><%=user.getEmail()%></p></td>
                <td>
                    <form action="./jspexample3.jsp" method="post">
                        <input type="hidden" name="userID" value="<%=idx%>">
                        <input type="hidden" name="action" value="removeUser">
                        <button type="submit" >Remove</button>
                    </form>
                    <form action="./jspexample3.jsp" method="post">
                        <input type="hidden" name="userID" value="<%=idx%>">
                        <input type="hidden" name="action" value="editUser">
                        <button type="submit" >Modify</button>
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </table>        
        
        <h2>commands</h2>
        <form action="./jspexample3.jsp" method="post">
            <p>user name <input type="text" name="userName" value=""></p>
            <p>email <input type="text" name="email" value=""></p>
            <input type="hidden" name="action" value="addUser">
            <button type="submit" >add name to list</button>
        </form> 
        <br>
        <form action="./jspexample3.jsp" method="get">
            <p>user name <input type="text" name="userName" value=""></p>
            <input type="hidden" name="action" value="removeUser">
            <button type="submit" >remove name from list</button>
        </form> 


    </body>
</html>

