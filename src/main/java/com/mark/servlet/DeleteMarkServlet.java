package com.mark.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.mark.dao.MarkDAO;

@WebServlet("/deleteMark")
public class DeleteMarkServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");

        try {
            int id = Integer.parseInt(req.getParameter("id"));

            MarkDAO dao = new MarkDAO();
            dao.deleteMark(id);

            res.getWriter().println("<h3>✅ Mark Deleted Successfully</h3>");

        } catch (Exception e) {
            e.printStackTrace();
            res.getWriter().println("<h3>❌ Error: " + e.getMessage() + "</h3>");
        }
    }
}