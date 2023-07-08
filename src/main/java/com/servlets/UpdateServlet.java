package com.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entities.Note;

import helper.FactoryProvider;


public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateServlet() {
        super();
        
    }

	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			// title, content, noteId fetch:
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int id = Integer.parseInt(request.getParameter("noteId").trim());

			// Getting the session factory and then call openSession Factory:
			Session session = FactoryProvider.getFactory().openSession();
			// begin transaction:
			Transaction trx = session.beginTransaction();

			// Fetch the Note with respect the noteId:
			Note note = session.get(Note.class, id);
			
			// Update in Note object:
			note.setTitle(title);
			note.setContent(content);
			note.setAddedDate(new Date());
			
			// Then simply update note:
			session.update(note);

			trx.commit();
			session.close();


			// After update the particular note, then simply redirect to the page of all_notes.jsp
			response.sendRedirect("all_notes.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
