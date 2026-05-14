<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.mark.model.StudentMark" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Added</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <p class="page-title">✅ Student Added</p>
    <p class="page-subtitle">The student record has been saved successfully.</p>

    <div class="alert alert-success">
        ✔ <%= request.getAttribute("message") %>
    </div>

    <%
        StudentMark s = (StudentMark) request.getAttribute("student");
        Integer generatedId = (Integer) request.getAttribute("generatedId");
    %>

    <% if (s != null) { %>
    <div class="id-badge">
        🪪 Generated Student ID: <span><%= generatedId %></span>
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
    <% } %>

    <div class="nav-bar">
        <a href="addMark" class="btn btn-primary">➕ Add Another</a>
        <a href="displayMark" class="btn btn-outline">📋 View All</a>
        <a href="index.jsp" class="btn btn-outline">🏠 Home</a>
    </div>
</div>
</body>
</html>
