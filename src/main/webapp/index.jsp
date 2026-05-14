<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Marks System</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h1 class="site-title">📚 Marks Manager</h1>
    <p class="site-tagline">Student Marks Management System</p>

    <div class="menu-grid">
        <a href="addMark" class="menu-card">
            <span class="icon">➕</span>
            <div class="label">Add Student</div>
            <div class="desc">Register a new record</div>
        </a>
        <a href="updateMark" class="menu-card">
            <span class="icon">✏️</span>
            <div class="label">Update Student</div>
            <div class="desc">Edit existing record</div>
        </a>
        <a href="markdelete.jsp" class="menu-card">
            <span class="icon">🗑️</span>
            <div class="label">Delete Student</div>
            <div class="desc">Remove a record</div>
        </a>
        <a href="displayMark" class="menu-card">
            <span class="icon">📋</span>
            <div class="label">Display All</div>
            <div class="desc">View all students</div>
        </a>
    </div>

    <a href="report_form.jsp" class="btn btn-outline btn-full" style="margin-top:14px;">
        📊 Generate Reports
    </a>
</div>
</body>
</html>
