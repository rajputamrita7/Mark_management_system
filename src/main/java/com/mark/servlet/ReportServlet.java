package com.mark.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.mark.dao.MarkDAO;
import com.mark.model.StudentMark;

import java.util.*;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            String type = req.getParameter("type");
            String value = req.getParameter("value");

            MarkDAO dao = new MarkDAO();
            List<StudentMark> list = dao.getReport(type, value);

            out.println("<html><head>");
            out.println("<link rel='stylesheet' href='css/style.css'>");
            out.println("<title>Report</title>");
            out.println("</head><body>");

            out.println("<div class='container'>");
            out.println("<h2>Report Results</h2>");

            if (list.isEmpty()) {
                out.println("<h3>No Records Found</h3>");
            } else {

                out.println("<table>");
                out.println("<tr><th>ID</th><th>Name</th><th>Subject</th><th>Marks</th><th>Date</th></tr>");

                for (StudentMark s : list) {
                    out.println("<tr>");
                    out.println("<td>" + s.getStudentID() + "</td>");
                    out.println("<td>" + s.getStudentName() + "</td>");
                    out.println("<td>" + s.getSubject() + "</td>");
                    out.println("<td>" + s.getMarks() + "</td>");
                    out.println("<td>" + s.getExamDate() + "</td>");
                    out.println("</tr>");
                }

                out.println("</table>");
            }

            out.println("<a href='index.jsp' class='back'>⬅ Back</a>");
            out.println("</div>");

            out.println("</body></html>");

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}