<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.mark.model.StudentMark, java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete Result</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container-wide">
    <p class="page-title">🗑️ Deletion Complete</p>
    <p class="page-subtitle">The student record has been removed from the system.</p>

    <div class="alert alert-success">
        ✔ <%= request.getAttribute("message") %>
    </div>

    <%
        List<StudentMark> students = (List<StudentMark>) request.getAttribute("students");
        Integer deletedId = (Integer) request.getAttribute("deletedId");
    %>

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
                <%-- Ghost row for deleted student --%>
                <% if (deletedId != null) { %>
                <tr class="row-deleted">
                    <td><%= deletedId %></td>
                    <td colspan="4">— Deleted this student —</td>
                </tr>
                <% } %>

                <% if (students == null || students.isEmpty()) { %>
                <tr>
                    <td colspan="5" class="empty-state">📭 No remaining student records.</td>
                </tr>
                <% } else {
                       for (StudentMark s : students) { %>
                <tr>
                    <td><%= s.getStudentID() %></td>
                    <td><%= s.getStudentName() %></td>
                    <td><%= s.getSubject() %></td>
                    <td><%= s.getMarks() %></td>
                    <td><%= s.getExamDate() %></td>
                </tr>
                <%     }
                   } %>
            </tbody>
        </table>
    </div>

    <div class="nav-bar">
        <a href="markdelete.jsp" class="btn btn-danger">🗑️ Delete Another</a>
        <a href="displayMark"    class="btn btn-outline">📋 View All</a>
        <a href="index.jsp"      class="btn btn-outline">🏠 Home</a>
    </div>
</div>
</body>
</html>
