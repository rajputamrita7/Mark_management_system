<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.mark.model.StudentMark, java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Result</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container-wide">
    <p class="page-title">✏️ Update Successful</p>
    <p class="page-subtitle">The record has been updated. The highlighted row shows the change.</p>

    <div class="alert alert-success">
        ✔ <%= request.getAttribute("message") %>
    </div>

    <%
        List<StudentMark> students = (List<StudentMark>) request.getAttribute("students");
        Integer updatedId = (Integer) request.getAttribute("updatedId");
    %>

    <% if (students == null || students.isEmpty()) { %>
    <div class="empty-state">📭 No student records found.</div>
    <% } else { %>
    <div class="table-wrapper">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Subject</th>
                    <th>Marks</th>
                    <th>Exam Date</th>
                </tr>
            </thead>
            <tbody>
            <% for (StudentMark s : students) {
                   boolean isUpdated = (updatedId != null && s.getStudentID() == updatedId);
            %>
                <tr class="<%= isUpdated ? "row-highlight" : "" %>">
                    <td><%= s.getStudentID() %> <%= isUpdated ? "✏️" : "" %></td>
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
        <a href="updateMark" class="btn btn-primary">✏️ Update Another</a>
        <a href="index.jsp" class="btn btn-outline">🏠 Home</a>
    </div>
</div>
</body>
</html>
