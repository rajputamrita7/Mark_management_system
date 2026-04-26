package com.mark.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.mark.dao.MarkDAO;
import com.mark.model.StudentMark;

import java.sql.Date;

@WebServlet("/updateMark")
public class UpdateMarkServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");

        try {
            StudentMark s = new StudentMark();

            s.setStudentID(Integer.parseInt(req.getParameter("id")));
            s.setStudentName(req.getParameter("name"));
            s.setSubject(req.getParameter("subject"));
            s.setMarks(Integer.parseInt(req.getParameter("marks")));
            s.setExamDate(Date.valueOf(req.getParameter("date")));

            MarkDAO dao = new MarkDAO();
            dao.updateMark(s);

            res.getWriter().println("<h3>✅ Mark Updated Successfully</h3>");

        } catch (Exception e) {
            e.printStackTrace();
            res.getWriter().println("<h3>❌ Error: " + e.getMessage() + "</h3>");
        }
    }
}