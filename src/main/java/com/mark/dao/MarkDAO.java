package com.mark.dao;

import java.sql.*;
import com.mark.model.StudentMark;
import java.util.*;

public class MarkDAO {

    private String url = "jdbc:mysql://localhost:3306/student_db";
    private String user = "root";
    private String pass = "1234"; // change if needed

    // Connection method
    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }

    // ADD MARK
    public void addMark(StudentMark s) throws Exception {

        Connection con = getConnection();

        String query = "INSERT INTO StudentMarks VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, s.getStudentID());
        ps.setString(2, s.getStudentName());
        ps.setString(3, s.getSubject());
        ps.setInt(4, s.getMarks());
        ps.setDate(5, s.getExamDate());

        ps.executeUpdate();

        con.close();
    }
    public static void main(String[] args) {
        try {
            MarkDAO dao = new MarkDAO();
            Connection con = dao.getConnection();

            if (con != null) {
                System.out.println("Connected to DB");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateMark(StudentMark s) throws Exception {

        Connection con = getConnection();

        String query = "UPDATE StudentMarks SET StudentName=?, Subject=?, Marks=?, ExamDate=? WHERE StudentID=?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, s.getStudentName());
        ps.setString(2, s.getSubject());
        ps.setInt(3, s.getMarks());
        ps.setDate(4, s.getExamDate());
        ps.setInt(5, s.getStudentID());

        int rows = ps.executeUpdate();

        if (rows == 0) {
            throw new Exception("Student not found!");
        }

        con.close();
    }
    public void deleteMark(int id) throws Exception {

        Connection con = getConnection();

        String query = "DELETE FROM StudentMarks WHERE StudentID=?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, id);

        int rows = ps.executeUpdate();

        if (rows == 0) {
            throw new Exception("Student not found!");
        }

        con.close();
    }
    public StudentMark getMark(int id) throws Exception {

        Connection con = getConnection();

        String query = "SELECT * FROM StudentMarks WHERE StudentID=?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        StudentMark s = null;

        if (rs.next()) {
            s = new StudentMark();
            s.setStudentID(rs.getInt(1));
            s.setStudentName(rs.getString(2));
            s.setSubject(rs.getString(3));
            s.setMarks(rs.getInt(4));
            s.setExamDate(rs.getDate(5));
        }

        con.close();
        return s;
    }
    

    public List<StudentMark> getReport(String type, String value) throws Exception {

        Connection con = getConnection();
        PreparedStatement ps = null;

        if (type.equals("marks")) {
            ps = con.prepareStatement("SELECT * FROM StudentMarks WHERE Marks > ?");
            ps.setInt(1, Integer.parseInt(value));
        } 
        else if (type.equals("subject")) {
            ps = con.prepareStatement("SELECT * FROM StudentMarks WHERE Subject = ?");
            ps.setString(1, value);
        } 
        else if (type.equals("top")) {
            ps = con.prepareStatement("SELECT * FROM StudentMarks ORDER BY Marks DESC LIMIT ?");
            ps.setInt(1, Integer.parseInt(value));
        }

        ResultSet rs = ps.executeQuery();

        List<StudentMark> list = new ArrayList<>();

        while (rs.next()) {
            StudentMark s = new StudentMark();
            s.setStudentID(rs.getInt(1));
            s.setStudentName(rs.getString(2));
            s.setSubject(rs.getString(3));
            s.setMarks(rs.getInt(4));
            s.setExamDate(rs.getDate(5));

            list.add(s);
        }

        con.close();
        return list;
    }
}