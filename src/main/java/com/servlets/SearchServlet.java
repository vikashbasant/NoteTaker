package com.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entities.Note;

import helper.FactoryProvider;


public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SearchServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			// Fetch SearchText:
			String searchText = request.getParameter("searchText");
			

			// Getting the session factory and then call openSession Factory:
			Session session = FactoryProvider.getFactory().openSession();
			// begin transaction:
			Transaction trx = session.beginTransaction();
			
			String queryString = "SELECT n FROM Note n WHERE lower(n.title) like lower(concat('%', :keyword, '%'))";
			Query query = session.createQuery(queryString).setParameter("keyword", searchText);

			List<Note> notes = query.list();
			

			trx.commit();
			session.close();


			// Set the notes as an attribute in the request object
            request.setAttribute("notes", notes);

            // Forward the request to the all_notes.jsp page
            request.getRequestDispatcher("search_notes.jsp").forward(request, response);

			// After delete the particular note, then simply redirect to the page of search_notes.jsp
			response.sendRedirect("search_notes.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
