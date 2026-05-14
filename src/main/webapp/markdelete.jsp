<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete Student</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <p class="page-title">🗑️ Delete Student</p>
    <p class="page-subtitle">Enter the Student ID to look up and confirm deletion.</p>

    <% if (request.getAttribute("lookupError") != null) { %>
    <div class="alert alert-error">⚠ <%= request.getAttribute("lookupError") %></div>
    <% } %>

    <form action="deleteMark" method="get">
        <div class="form-group">
            <label for="id">Student ID</label>
            <input type="number" id="id" name="id" placeholder="e.g. 5" min="1"
                   value="<%= request.getAttribute("searchId") != null ? request.getAttribute("searchId") : "" %>">
        </div>
        <button type="submit" class="btn btn-danger btn-full">🔍 Find Student</button>
    </form>

    <div class="nav-bar">
        <a href="index.jsp" class="btn btn-outline">🏠 Home</a>
    </div>
</div>
</body>
</html>
