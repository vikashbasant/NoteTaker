package com.servlets;

import com.entities.Note;
import helper.FactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


public class SaveNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public SaveNoteServlet() {
        super();
    }

	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			// title, content fetch:
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			// create an object of Note:
			Note note = new Note(title, content, new Date());
			System.out.println(note.getId() + " : " + note.getTitle());
			
			// Getting the session factory and then call openSession Factory:
			Session s = FactoryProvider.getFactory().openSession();
			
			// open transaction:
			Transaction tx = s.beginTransaction();
			
			// simply save the transaction:
			s.save(note);
			
			tx.commit();
			s.close();
			
			// to show the data on browser:
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<h1 style='text-align: center;'>Note is added Successfully</h1>");
			out.println("<h1 style='text-align: center;'><a href='all_notes.jsp'>View all Notes</a></h1>");
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
