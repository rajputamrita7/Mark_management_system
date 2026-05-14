<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.mark.model.StudentMark, java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Report Results</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container-wide">
    <p class="page-title">📊 Report Results</p>

    <%
        String minM    = (String) request.getAttribute("minMarks");
        String maxM    = (String) request.getAttribute("maxMarks");
        String subject = (String) request.getAttribute("subject");
        String topN    = (String) request.getAttribute("topN");

        // Build a human-readable filter description
        StringBuilder filters = new StringBuilder();
        if (minM    != null && !minM.isEmpty())    filters.append("Marks ≥ ").append(minM).append("  ");
        if (maxM    != null && !maxM.isEmpty())    filters.append("Marks ≤ ").append(maxM).append("  ");
        if (subject != null && !subject.isEmpty()) filters.append("Subject: ").append(subject).append("  ");
        if (topN    != null && !topN.isEmpty())    filters.append("Top ").append(topN).append(" students");
        if (filters.length() == 0) filters.append("All students");
    %>

    <p class="page-subtitle">Filters applied: <strong><%= filters.toString().trim() %></strong></p>

    <%
        List<StudentMark> students = (List<StudentMark>) request.getAttribute("students");
    %>

    <% if (students == null || students.isEmpty()) { %>
    <div class="alert alert-info">📭 No records match the selected filters.</div>
    <% } else { %>
    <div class="alert alert-success">
        ✔ Found <strong><%= students.size() %></strong> record(s) matching your filters.
    </div>

    <div class="table-wrapper">
        <table>
            <thead>
                <tr>
                    <th>#</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Subject</th>
                    <th>Marks</th>
                    <th>Exam Date</th>
                </tr>
            </thead>
            <tbody>
            <% int rank = 1; for (StudentMark s : students) { %>
                <tr>
                    <td><%= rank++ %></td>
                    <td><%= s.getStudentID() %></td>
                    <td><%= s.getStudentName() %></td>
                    <td><%= s.getSubject() %></td>
                    <td><%= s.getMarks() %></td>
                    <td><%= s.getExamDate() %></td>
                </tr>
            <% } %>
            </tbody>
        </table>
    </div>
    <% } %>

    <div class="nav-bar">
        <a href="report_form.jsp" class="btn btn-primary">📊 New Report</a>
        <a href="displayMark"     class="btn btn-outline">📋 View All</a>
        <a href="index.jsp"       class="btn btn-outline">🏠 Home</a>
    </div>
</div>
</body>
</html>
