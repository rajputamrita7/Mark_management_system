package com.mark.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.mark.dao.MarkDAO;
import com.mark.model.StudentMark;

import java.util.List;

@WebServlet("/deleteMark")
public class DeleteMarkServlet extends HttpServlet {

    // GET: fetch student and show confirmation page
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String idStr = req.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            req.getRequestDispatcher("markdelete.jsp").forward(req, res);
            return;
        }

        try {
            int id = Integer.parseInt(idStr.trim());
            MarkDAO dao = new MarkDAO();
            StudentMark s = dao.getMark(id);

            if (s == null) {
                req.setAttribute("lookupError", "No student found with ID: " + id);
                req.setAttribute("searchId", id);
                req.getRequestDispatcher("markdelete.jsp").forward(req, res);
            } else {
                req.setAttribute("student", s);
                req.getRequestDispatcher("deleteConfirm.jsp").forward(req, res);
            }
        } catch (NumberFormatException e) {
            req.setAttribute("lookupError", "Invalid Student ID. Please enter a number.");
            req.getRequestDispatcher("markdelete.jsp").forward(req, res);
        } catch (Exception e) {
            req.setAttribute("error", "Database error: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }

    // POST: perform delete, show all students with ghost row
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String idStr   = req.getParameter("id")     != null ? req.getParameter("id").trim()    : "";
        String confirm = req.getParameter("confirm") != null ? req.getParameter("confirm").trim() : "";

        if (!"yes".equals(confirm)) {
            // User cancelled
            res.sendRedirect("index.jsp");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            MarkDAO dao = new MarkDAO();
            boolean deleted = dao.deleteMark(id);

            List<StudentMark> all = dao.getAllMarks();
            req.setAttribute("students", all);
            req.setAttribute("deletedId", id);

            if (deleted) {
                req.setAttribute("message", "Student " + id + " has been deleted.");
            } else {
                req.setAttribute("message", "Student ID " + id + " was not found.");
            }
            req.getRequestDispatcher("deleteResult.jsp").forward(req, res);

        } catch (Exception e) {
            req.setAttribute("error", "Delete failed: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }
}
