<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Generate Report</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <p class="page-title">📊 Generate Report</p>
    <p class="page-subtitle">Use any combination of filters. Leave a field blank to ignore it.</p>

    <% if (request.getAttribute("filterError") != null) { %>
    <div class="alert alert-error">⚠ <%= request.getAttribute("filterError") %></div>
    <% } %>

    <form action="report" method="post">

        <hr class="divider">
        <p style="font-weight:700; color:#475569; font-size:0.85rem; text-transform:uppercase; letter-spacing:0.05em; margin-bottom:14px;">📈 Marks Range</p>

        <div class="form-row">
            <div class="form-group">
                <label for="minMarks">Min Marks (≥)</label>
                <input type="number" id="minMarks" name="minMarks" placeholder="e.g. 50" min="0" max="100"
                       value="<%= request.getAttribute("minMarks") != null ? request.getAttribute("minMarks") : "" %>">
            </div>
            <div class="form-group">
                <label for="maxMarks">Max Marks (≤)</label>
                <input type="number" id="maxMarks" name="maxMarks" placeholder="e.g. 90" min="0" max="100"
                       value="<%= request.getAttribute("maxMarks") != null ? request.getAttribute("maxMarks") : "" %>">
            </div>
        </div>

        <hr class="divider">
        <p style="font-weight:700; color:#475569; font-size:0.85rem; text-transform:uppercase; letter-spacing:0.05em; margin-bottom:14px;">📚 Subject Filter</p>

        <div class="form-group">
            <label for="subject">Subject</label>
            <select id="subject" name="subject">
                <option value="">-- All Subjects --</option>
                <option value="Mathematics"      <%= "Mathematics".equals(request.getAttribute("subject"))      ? "selected" : "" %>>Mathematics</option>
                <option value="Physics"          <%= "Physics".equals(request.getAttribute("subject"))          ? "selected" : "" %>>Physics</option>
                <option value="Chemistry"        <%= "Chemistry".equals(request.getAttribute("subject"))        ? "selected" : "" %>>Chemistry</option>
                <option value="Biology"          <%= "Biology".equals(request.getAttribute("subject"))          ? "selected" : "" %>>Biology</option>
                <option value="Computer Science" <%= "Computer Science".equals(request.getAttribute("subject")) ? "selected" : "" %>>Computer Science</option>
                <option value="English"          <%= "English".equals(request.getAttribute("subject"))          ? "selected" : "" %>>English</option>
            </select>
        </div>

        <hr class="divider">
        <p style="font-weight:700; color:#475569; font-size:0.85rem; text-transform:uppercase; letter-spacing:0.05em; margin-bottom:14px;">🏆 Top N Students</p>

        <div class="form-group">
            <label for="topN">Show Top N (by marks)</label>
            <input type="number" id="topN" name="topN" placeholder="e.g. 5 (leave blank for all)" min="1"
                   value="<%= request.getAttribute("topN") != null ? request.getAttribute("topN") : "" %>">
        </div>

        <button type="submit" class="btn btn-primary btn-full">📊 Generate Report</button>
    </form>

    <div class="nav-bar">
        <a href="index.jsp" class="btn btn-outline">🏠 Home</a>
    </div>
</div>
</body>
</html>
