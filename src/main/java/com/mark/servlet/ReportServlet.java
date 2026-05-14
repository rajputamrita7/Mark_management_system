package com.mark.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.mark.dao.MarkDAO;
import com.mark.model.StudentMark;

import java.util.List;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getRequestDispatcher("report_form.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String minStr     = req.getParameter("minMarks")  != null ? req.getParameter("minMarks").trim()  : "";
        String maxStr     = req.getParameter("maxMarks")  != null ? req.getParameter("maxMarks").trim()  : "";
        String subject    = req.getParameter("subject")   != null ? req.getParameter("subject").trim()   : "";
        String topNStr    = req.getParameter("topN")      != null ? req.getParameter("topN").trim()      : "";

        Integer minMarks = null;
        Integer maxMarks = null;
        int topN = 0;

        String filterError = null;

        try {
            if (!minStr.isEmpty()) minMarks = Integer.parseInt(minStr);
            if (!maxStr.isEmpty()) maxMarks = Integer.parseInt(maxStr);
            if (!topNStr.isEmpty()) topN = Integer.parseInt(topNStr);
        } catch (NumberFormatException e) {
            filterError = "Please enter valid numbers for marks / top N.";
        }

        if (filterError != null) {
            req.setAttribute("filterError", filterError);
            req.getRequestDispatcher("report_form.jsp").forward(req, res);
            return;
        }

        try {
            MarkDAO dao = new MarkDAO();
            List<StudentMark> list = dao.getReport(minMarks, maxMarks, subject, topN);

            req.setAttribute("students", list);
            req.setAttribute("minMarks", minStr);
            req.setAttribute("maxMarks", maxStr);
            req.setAttribute("subject",  subject);
            req.setAttribute("topN",     topNStr);
            req.getRequestDispatcher("reportResult.jsp").forward(req, res);

        } catch (Exception e) {
            req.setAttribute("error", "Report failed: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }
}
