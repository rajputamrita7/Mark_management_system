package com.mark.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.mark.dao.MarkDAO;
import com.mark.model.StudentMark;

import java.sql.Date;
import java.util.List;

@WebServlet("/updateMark")
public class UpdateMarkServlet extends HttpServlet {

    // GET: look up student by ID, show pre-filled form (or error)
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String idStr = req.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            req.getRequestDispatcher("markupdate.jsp").forward(req, res);
            return;
        }

        try {
            int id = Integer.parseInt(idStr.trim());
            MarkDAO dao = new MarkDAO();
            StudentMark s = dao.getMark(id);

            if (s == null) {
                req.setAttribute("lookupError", "No student found with ID: " + id);
                req.setAttribute("searchId", id);
                req.getRequestDispatcher("markupdate.jsp").forward(req, res);
            } else {
                req.setAttribute("student", s);
                req.getRequestDispatcher("markupdate.jsp").forward(req, res);
            }
        } catch (NumberFormatException e) {
            req.setAttribute("lookupError", "Invalid Student ID. Please enter a number.");
            req.getRequestDispatcher("markupdate.jsp").forward(req, res);
        } catch (Exception e) {
            req.setAttribute("error", "Database error: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }

    // POST: perform the update, then display all students with highlight
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String idStr   = req.getParameter("id")      != null ? req.getParameter("id").trim()      : "";
        String name    = req.getParameter("name")    != null ? req.getParameter("name").trim()    : "";
        String subject = req.getParameter("subject") != null ? req.getParameter("subject").trim() : "";
        String marksStr= req.getParameter("marks")   != null ? req.getParameter("marks").trim()   : "";
        String dateStr = req.getParameter("date")    != null ? req.getParameter("date").trim()    : "";

        try {
            int id    = Integer.parseInt(idStr);
            int marks = Integer.parseInt(marksStr);

            StudentMark s = new StudentMark();
            s.setStudentID(id);
            s.setStudentName(name);
            s.setSubject(subject);
            s.setMarks(marks);
            s.setExamDate(Date.valueOf(dateStr));

            MarkDAO dao = new MarkDAO();
            boolean updated = dao.updateMark(s);

            if (updated) {
                List<StudentMark> all = dao.getAllMarks();
                req.setAttribute("students", all);
                req.setAttribute("updatedId", id);
                req.setAttribute("message", "Student ID " + id + " updated successfully!");
                req.getRequestDispatcher("updateResult.jsp").forward(req, res);
            } else {
                req.setAttribute("lookupError", "No student found with ID: " + id);
                req.getRequestDispatcher("markupdate.jsp").forward(req, res);
            }
        } catch (Exception e) {
            req.setAttribute("error", "Update failed: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }
}
