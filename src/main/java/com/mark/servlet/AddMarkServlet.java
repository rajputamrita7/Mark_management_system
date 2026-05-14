package com.mark.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.mark.dao.MarkDAO;
import com.mark.model.StudentMark;

import java.sql.Date;

@WebServlet("/addMark")
public class AddMarkServlet extends HttpServlet {

    // GET: show the add form with next-ID preview
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            MarkDAO dao = new MarkDAO();
            int nextId = dao.getNextId();
            req.setAttribute("nextId", nextId);
        } catch (Exception e) {
            req.setAttribute("nextId", "?");
        }
        req.getRequestDispatcher("markadd.jsp").forward(req, res);
    }

    // POST: validate and insert
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String name    = req.getParameter("name")    != null ? req.getParameter("name").trim()    : "";
        String subject = req.getParameter("subject") != null ? req.getParameter("subject").trim() : "";
        String marksStr= req.getParameter("marks")   != null ? req.getParameter("marks").trim()   : "";
        String dateStr = req.getParameter("date")    != null ? req.getParameter("date").trim()    : "";

        // ── Validation ────────────────────────────────────────────────────────
        String nameError  = null;
        String marksError = null;

        if (!name.matches("[a-zA-Z ]+")) {
            nameError = "Name must contain alphabets only.";
        }

        int marks = 0;
        try {
            marks = Integer.parseInt(marksStr);
            if (marks <= 0) marksError = "Marks must be greater than 0.";
        } catch (NumberFormatException e) {
            marksError = "Marks must be a valid number.";
        }

        if (nameError != null || marksError != null) {
            // Re-show form with errors
            req.setAttribute("nameError",  nameError);
            req.setAttribute("marksError", marksError);
            req.setAttribute("oldName",    name);
            req.setAttribute("oldSubject", subject);
            req.setAttribute("oldMarks",   marksStr);
            req.setAttribute("oldDate",    dateStr);
            try {
                req.setAttribute("nextId", new MarkDAO().getNextId());
            } catch (Exception ex) {
                req.setAttribute("nextId", "?");
            }
            req.getRequestDispatcher("markadd.jsp").forward(req, res);
            return;
        }

        // ── Insert ────────────────────────────────────────────────────────────
        try {
            StudentMark s = new StudentMark();
            s.setStudentName(name);
            s.setSubject(subject);
            s.setMarks(marks);
            s.setExamDate(Date.valueOf(dateStr));

            MarkDAO dao = new MarkDAO();
            int generatedId = dao.addMark(s);

            // Fetch inserted record to display
            StudentMark inserted = dao.getMark(generatedId);

            req.setAttribute("generatedId", generatedId);
            req.setAttribute("student", inserted);
            req.setAttribute("message", "Student record added successfully!");
            req.getRequestDispatcher("addResult.jsp").forward(req, res);

        } catch (Exception e) {
            req.setAttribute("error", "Database error: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }
}
