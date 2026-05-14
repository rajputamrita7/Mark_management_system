<html><%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.mark.model.StudentMark" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Student</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">

<%
    StudentMark student = (StudentMark) request.getAttribute("student");
    String lookupError  = (String)      request.getAttribute("lookupError");
%>

<% if (student == null) { %>
    <%-- ── Step 1: Search form ─────────────────────────────────────────────── --%>
    <p class="page-title">✏️ Update Student</p>
    <p class="page-subtitle">Enter the Student ID to look up the record.</p>

    <% if (lookupError != null) { %>
    <div class="alert alert-error">⚠ <%= lookupError %></div>
    <% } %>

    <form action="updateMark" method="get">
        <div class="form-group">
            <label for="id">Student ID</label>
            <input type="number" id="id" name="id" placeholder="e.g. 3" min="1"
                   value="<%= request.getAttribute("searchId") != null ? request.getAttribute("searchId") : "" %>">
        </div>
        <button type="submit" class="btn btn-primary btn-full">🔍 Find Student</button>
    </form>

<% } else { %>
    <%-- ── Step 2: Pre-filled edit form ───────────────────────────────────── --%>
    <p class="page-title">✏️ Edit Student</p>
    <p class="page-subtitle">Update the fields below and click Save Changes.</p>

    <div class="id-badge">
        🪪 Editing Student ID: <span><%= student.getStudentID() %></span>
    </div>

    <form action="updateMark" method="post">
        <input type="hidden" name="id" value="<%= student.getStudentID() %>">

        <div class="form-group">
            <label for="name">Student Name</label>
            <input type="text" id="name" name="name"
                   value="<%= student.getStudentName() %>" placeholder="Student Name">
        </div>

        <div class="form-group">
            <label for="subject">Subject</label>
            <select id="subject" name="subject">
                <option value="">-- Select Subject --</option>
                <option value="Mathematics"      <%= "Mathematics".equals(student.getSubject())      ? "selected" : "" %>>Mathematics</option>
                <option value="Physics"          <%= "Physics".equals(student.getSubject())          ? "selected" : "" %>>Physics</option>
                <option value="Chemistry"        <%= "Chemistry".equals(student.getSubject())        ? "selected" : "" %>>Chemistry</option>
                <option value="Biology"          <%= "Biology".equals(student.getSubject())          ? "selected" : "" %>>Biology</option>
                <option value="Computer Science" <%= "Computer Science".equals(student.getSubject()) ? "selected" : "" %>>Computer Science</option>
                <option value="English"          <%= "English".equals(student.getSubject())          ? "selected" : "" %>>English</option>
            </select>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="marks">Marks</label>
                <input type="number" id="marks" name="marks" min="1" max="100"
                       value="<%= student.getMarks() %>" placeholder="Marks">
            </div>
            <div class="form-group">
                <label for="date">Exam Date</label>
                <input type="date" id="date" name="date"
                       value="<%= student.getExamDate() %>">
            </div>
        </div>

        <button type="submit" class="btn btn-primary btn-full">💾 Save Changes</button>
    </form>
<% } %>

    <div class="nav-bar">
        <a href="index.jsp" class="btn btn-outline">🏠 Home</a>
    </div>
</div>
</body>
</html>
