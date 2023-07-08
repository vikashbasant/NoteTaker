package com.servlets;

import com.entities.Note;
import helper.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DeleteServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Getting the note_id from parameter:
			int noteId = Integer.parseInt(request.getParameter("note_id").trim());

			// Getting the session factory and then call openSession Factory:
			Session session = FactoryProvider.getFactory().openSession();
			// begin transaction:
			Transaction trx = session.beginTransaction();

			// fetching note from session:
			Note note = session.get(Note.class, noteId);

			// Then simply delete note:
			session.delete(note);

			trx.commit();
			session.close();


			// After delete the particular note, then simply redirect to the page of all_notes.jsp
			response.sendRedirect("all_notes.jsp");


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	

}
