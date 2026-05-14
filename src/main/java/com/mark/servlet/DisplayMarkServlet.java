package com.mark.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.mark.dao.MarkDAO;
import com.mark.model.StudentMark;

import java.util.List;

@WebServlet("/displayMark")
public class DisplayMarkServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        handle(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        handle(req, res);
    }

    private void handle(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            MarkDAO dao = new MarkDAO();
            List<StudentMark> list = dao.getAllMarks();
            req.setAttribute("students", list);
            req.getRequestDispatcher("markdisplay.jsp").forward(req, res);
        } catch (Exception e) {
            req.setAttribute("error", "Could not load student records: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }
}
