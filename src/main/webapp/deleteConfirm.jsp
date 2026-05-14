<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.mark.model.StudentMark" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirm Deletion</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <p class="page-title">⚠️ Confirm Deletion</p>
    <p class="page-subtitle">Review the record below before permanently deleting it.</p>

    <%
        StudentMark s = (StudentMark) request.getAttribute("student");
    %>

    <% if (s != null) { %>
    <div class="alert alert-error">
        🚨 You are about to delete Student ID <strong><%= s.getStudentID() %></strong>. This action cannot be undone.
    </div>

    <div class="detail-card">
        <div class="detail-row">
            <span class="detail-label">Student ID</span>
            <span class="detail-value"><%= s.getStudentID() %></span>
        </div>
        <div class="detail-row">
            <span class="detail-label">Student Name</span>
            <span class="detail-value"><%= s.getStudentName() %></span>
        </div>
        <div class="detail-row">
            <span class="detail-label">Subject</span>
            <span class="detail-value"><%= s.getSubject() %></span>
        </div>
        <div class="detail-row">
            <span class="detail-label">Marks</span>
            <span class="detail-value"><%= s.getMarks() %></span>
        </div>
        <div class="detail-row">
            <span class="detail-label">Exam Date</span>
            <span class="detail-value"><%= s.getExamDate() %></span>
        </div>
    </div>

    <div class="confirm-box">
        <p>Are you sure you want to permanently delete this student record?</p>
        <div class="btn-group">
            <form action="deleteMark" method="post" style="display:inline;">
                <input type="hidden" name="id"      value="<%= s.getStudentID() %>">
                <input type="hidden" name="confirm" value="yes">
                <button type="submit" class="btn btn-danger">✅ Yes, Delete</button>
            </form>
            <a href="markdelete.jsp" class="btn btn-outline">❌ No, Cancel</a>
        </div>
    </div>
    <% } %>

    <div class="nav-bar">
        <a href="index.jsp" class="btn btn-outline">🏠 Home</a>
    </div>
</div>
</body>
</html>
