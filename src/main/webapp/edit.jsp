<%@page import="com.entities.Note"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="helper.FactoryProvider"%>
<%@page import="org.hibernate.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Update Notes</title>
    <%@include file="all_js_css.jsp"%>
</head>
<body>
    <div class = "container-fluid">
        <%@include file="navbar.jsp"%>
        <br>
        <h1>Edit Note!</h1>
        <%
            // Getting the note_id from parameter:
            int noteId = Integer.parseInt(request.getParameter("note_id").trim());
        	
     		// Getting the session factory and then call openSession Factory:
     		Session s = FactoryProvider.getFactory().openSession();
     	
     		// fetching note from session:
     		Note note = s.get(Note.class, noteId);
        %>
        
        <!-- This is the add form -->
        <form action="UpdateServlet" method="post">
        
        	<input value="<%= note.getId() %>" name="noteId" type="hidden"/>
        	
            <div class="form-group">
                <label for="title">Note Title</label>
                <input 
                name="title" 
                required 
                type="text" 
                class="form-control" 
                id="title" 
                aria-describedby="emailHelp" 
                placeholder="Enter Note Title Here"
                value="<%= note.getTitle() %>"/>
            </div>

            <div class="form-group">
                <label for="content">Note Content</label>
                <textarea 
                name="content" 
                required 
                id="content" 
                placeholder="Enter Your Content Here" 
                class="form-control" 
                style="height: 200px;"><%= note.getContent() %></textarea>
            </div>

            <div class="container-fluid text-center">
                <button type="submit" class="btn btn-success">Update Note</button>
            </div>
        </form>
    </div>

</body>
</html>
