package com.mark.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.mark.dao.MarkDAO;
import com.mark.model.StudentMark;

@WebServlet("/displayMark")
public class DisplayMarkServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            int id = Integer.parseInt(req.getParameter("id"));

            MarkDAO dao = new MarkDAO();
            StudentMark s = dao.getMark(id);

            out.println("<html><head>");
            out.println("<link rel='stylesheet' href='css/style.css'>");
            out.println("<title>Display Result</title>");
            out.println("</head><body>");

            out.println("<div class='container'>");
            out.println("<h2>Student Details</h2>");

            if (s != null) {

                out.println("<table>");
                out.println("<tr><th>ID</th><th>Name</th><th>Subject</th><th>Marks</th><th>Date</th></tr>");

                out.println("<tr>");
                out.println("<td>" + s.getStudentID() + "</td>");
                out.println("<td>" + s.getStudentName() + "</td>");
                out.println("<td>" + s.getSubject() + "</td>");
                out.println("<td>" + s.getMarks() + "</td>");
                out.println("<td>" + s.getExamDate() + "</td>");
                out.println("</tr>");

                out.println("</table>");

            } else {
                out.println("<h3>❌ Student Not Found</h3>");
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