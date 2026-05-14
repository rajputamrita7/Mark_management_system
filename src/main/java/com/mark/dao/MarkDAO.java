package com.mark.dao;

import java.sql.*;
import java.util.*;
import com.mark.model.StudentMark;

public class MarkDAO {

    private String url  = "jdbc:mysql://localhost:3306/student_db";
    private String user = "root";
    private String pass = "1234"; // change if needed

    // Predefined subjects constant
    public static final String[] SUBJECTS = {
        "Mathematics", "Physics", "Chemistry", "Biology", "Computer Science", "English"
    };

    // ─── Connection ────────────────────────────────────────────────────────────
    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }

    // ─── Compute next gap-fill ID on an EXISTING open connection ──────────────
    // Private helper — always called on the same connection as the insert
    // so the preview and the actual insert use identical logic.
    private int computeNextId(Connection con) throws Exception {
        PreparedStatement ps = con.prepareStatement(
            "SELECT StudentID FROM StudentMarks ORDER BY StudentID ASC FOR UPDATE"
        );
        ResultSet rs = ps.executeQuery();
        int expected = 1;
        while (rs.next()) {
            int existing = rs.getInt(1);
            if (existing != expected) return expected;
            expected++;
        }
        return expected;
    }

    // ─── Public preview: what ID will the NEXT insert receive? ────────────────
    public int getNextId() throws Exception {
        Connection con = getConnection();
        try {
            // Use a transaction so the SELECT sees a consistent snapshot
            con.setAutoCommit(false);
            int id = computeNextId(con);
            con.commit();
            return id;
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
            con.close();
        }
    }

    // ─── Add Student: preview ID and insert in ONE transaction ────────────────
    public int addMark(StudentMark s) throws Exception {
        Connection con = getConnection();
        try {
            con.setAutoCommit(false);

            // Compute the ID on the SAME connection (with row lock) so it
            // matches the preview exactly and is race-condition-safe.
            int nextId = computeNextId(con);

            String query = "INSERT INTO StudentMarks (StudentID, StudentName, Subject, Marks, ExamDate) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, nextId);
            ps.setString(2, s.getStudentName());
            ps.setString(3, s.getSubject());
            ps.setInt(4, s.getMarks());
            ps.setDate(5, s.getExamDate());
            ps.executeUpdate();

            con.commit();
            return nextId;
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
            con.close();
        }
    }

    // ─── Update Student ────────────────────────────────────────────────────────
    public boolean updateMark(StudentMark s) throws Exception {
        Connection con = getConnection();
        try {
            String query = "UPDATE StudentMarks SET StudentName=?, Subject=?, Marks=?, ExamDate=? WHERE StudentID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, s.getStudentName());
            ps.setString(2, s.getSubject());
            ps.setInt(3, s.getMarks());
            ps.setDate(4, s.getExamDate());
            ps.setInt(5, s.getStudentID());
            int rows = ps.executeUpdate();
            return rows > 0;
        } finally {
            con.close();
        }
    }

    // ─── Delete Student ────────────────────────────────────────────────────────
    public boolean deleteMark(int id) throws Exception {
        Connection con = getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM StudentMarks WHERE StudentID=?");
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;
        } finally {
            con.close();
        }
    }

    // ─── Get Single Student ────────────────────────────────────────────────────
    public StudentMark getMark(int id) throws Exception {
        Connection con = getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM StudentMarks WHERE StudentID=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                StudentMark s = new StudentMark();
                s.setStudentID(rs.getInt("StudentID"));
                s.setStudentName(rs.getString("StudentName"));
                s.setSubject(rs.getString("Subject"));
                s.setMarks(rs.getInt("Marks"));
                s.setExamDate(rs.getDate("ExamDate"));
                return s;
            }
            return null;
        } finally {
            con.close();
        }
    }

    // ─── Get All Students ──────────────────────────────────────────────────────
    public List<StudentMark> getAllMarks() throws Exception {
        Connection con = getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM StudentMarks ORDER BY StudentID ASC");
            ResultSet rs = ps.executeQuery();
            List<StudentMark> list = new ArrayList<>();
            while (rs.next()) {
                StudentMark s = new StudentMark();
                s.setStudentID(rs.getInt("StudentID"));
                s.setStudentName(rs.getString("StudentName"));
                s.setSubject(rs.getString("Subject"));
                s.setMarks(rs.getInt("Marks"));
                s.setExamDate(rs.getDate("ExamDate"));
                list.add(s);
            }
            return list;
        } finally {
            con.close();
        }
    }

    // ─── Advanced Report ───────────────────────────────────────────────────────
    /**
     * @param minMarks  null = no lower bound
     * @param maxMarks  null = no upper bound
     * @param subject   null or "" = all subjects
     * @param topN      0 = no limit
     */
    public List<StudentMark> getReport(Integer minMarks, Integer maxMarks,
                                       String subject, int topN) throws Exception {
        Connection con = getConnection();
        try {
            StringBuilder sb = new StringBuilder("SELECT * FROM StudentMarks WHERE 1=1");

            if (minMarks != null) sb.append(" AND Marks >= ?");
            if (maxMarks != null) sb.append(" AND Marks <= ?");
            if (subject  != null && !subject.trim().isEmpty()) sb.append(" AND Subject = ?");

            sb.append(" ORDER BY Marks DESC");

            if (topN > 0) sb.append(" LIMIT ?");

            PreparedStatement ps = con.prepareStatement(sb.toString());
            int idx = 1;
            if (minMarks != null) ps.setInt(idx++, minMarks);
            if (maxMarks != null) ps.setInt(idx++, maxMarks);
            if (subject  != null && !subject.trim().isEmpty()) ps.setString(idx++, subject);
            if (topN > 0) ps.setInt(idx, topN);

            ResultSet rs = ps.executeQuery();
            List<StudentMark> list = new ArrayList<>();
            while (rs.next()) {
                StudentMark s = new StudentMark();
                s.setStudentID(rs.getInt("StudentID"));
                s.setStudentName(rs.getString("StudentName"));
                s.setSubject(rs.getString("Subject"));
                s.setMarks(rs.getInt("Marks"));
                s.setExamDate(rs.getDate("ExamDate"));
                list.add(s);
            }
            return list;
        } finally {
            con.close();
        }
    }
}
