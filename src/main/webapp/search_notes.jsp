<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.entities.Note"%>
<%@page import="java.util.List"%>
<%@page import="helper.FactoryProvider"%>
<%@page import="org.hibernate.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>All Notes: Note Taker</title>
    <%@include file="all_js_css.jsp"%>
</head>
<body>
    <div class="container-fluid">
        <%@include file="navbar.jsp"%>
        <br>
        <h1 class="text-uppercase">Search Notes:</h1>

        <div class="row">
            <div class="col-12">
                <%
                List<Note> notes = (List<Note>) request.getAttribute("notes"); 
                if (notes != null && !notes.isEmpty()) { 
                   for (Note note : notes) {
                %>
                <div class="card mt-3">
                    <div class="card-header text-white bg-info">
                        <div class="d-flex align-items-center">
                            <img class="card-img-top m-4" style="max-width: 50px" src="img/notepad.png" alt="Card image cap">
                            <h5 class="card-title m-0" style="color: #8B4513;"><%= note.getTitle() %></h5>
                        </div>
                    </div>
                    <div class="card-body text-white bg-dark">
                        <p class="card-text mt-3"><%= note.getContent() %></p>
                        <%
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
							String formattedDate = dateFormat.format(note.getAddedDate());
						%>
                        
                        <p class="card-text mt-3 text-right added-date" style="color: #FF4500;">Added Date:&nbsp; <b class="text-primary"><%= formattedDate %></b></p>
                        <div class="container text-center">
                            <a href="DeleteServlet?note_id=<%= note.getId() %>" class="btn btn-danger">Delete</a>
                            <a href="edit.jsp?note_id=<%= note.getId() %>" class="btn btn-primary">Update</a>
                        </div>
                    </div>
                </div>
                <% } 
                }%>
            </div>
        </div>
    </div>
</body>
</html>
