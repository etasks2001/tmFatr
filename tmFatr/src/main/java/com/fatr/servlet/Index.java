package com.fatr.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fatr.model.Serie;
import com.fatr.model.SerieId;

@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Index() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("nf");
		EntityManager em = factory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			SerieId id = new SerieId(1, 55);
			Integer nnf_inicial = 791;
			Integer nnf_final = 900;
			Serie serie = new Serie(id, nnf_inicial, nnf_final);

			transaction.begin();
			em.persist(serie);
			transaction.commit();
			em.close();

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index.html");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			transaction.rollback();
			em.close();
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
