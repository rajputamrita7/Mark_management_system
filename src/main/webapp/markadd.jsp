<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Student</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <p class="page-title">➕ Add Student</p>
    <p class="page-subtitle">Fill in the details below to register a new student record.</p>

    <%-- Next ID preview --%>
    <% Object nextIdObj = request.getAttribute("nextId"); %>
    <% if (nextIdObj != null) { %>
    <div class="id-badge">
        🪪 Next Student ID will be: <span><%= nextIdObj %></span>
    </div>
    <% } %>

    <form action="addMark" method="post" novalidate>

        <div class="form-group">
            <label for="name">Student Name</label>
            <input type="text" id="name" name="name"
                   placeholder="e.g. Arjun Kumar"
                   value="<%= request.getAttribute("oldName") != null ? request.getAttribute("oldName") : "" %>"
                   class="<%= request.getAttribute("nameError") != null ? "is-error" : "" %>">
            <% if (request.getAttribute("nameError") != null) { %>
            <span class="field-error">⚠ <%= request.getAttribute("nameError") %></span>
            <% } %>
        </div>

        <div class="form-group">
            <label for="subject">Subject</label>
            <select id="subject" name="subject">
                <option value="">-- Select Subject --</option>
                <option value="Mathematics"      <%= "Mathematics".equals(request.getAttribute("oldSubject"))      ? "selected" : "" %>>Mathematics</option>
                <option value="Physics"          <%= "Physics".equals(request.getAttribute("oldSubject"))          ? "selected" : "" %>>Physics</option>
                <option value="Chemistry"        <%= "Chemistry".equals(request.getAttribute("oldSubject"))        ? "selected" : "" %>>Chemistry</option>
                <option value="Biology"          <%= "Biology".equals(request.getAttribute("oldSubject"))          ? "selected" : "" %>>Biology</option>
                <option value="Computer Science" <%= "Computer Science".equals(request.getAttribute("oldSubject")) ? "selected" : "" %>>Computer Science</option>
                <option value="English"          <%= "English".equals(request.getAttribute("oldSubject"))          ? "selected" : "" %>>English</option>
            </select>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="marks">Marks</label>
                <input type="number" id="marks" name="marks" min="1" max="100"
                       placeholder="e.g. 85"
                       value="<%= request.getAttribute("oldMarks") != null ? request.getAttribute("oldMarks") : "" %>"
                       class="<%= request.getAttribute("marksError") != null ? "is-error" : "" %>">
                <% if (request.getAttribute("marksError") != null) { %>
                <span class="field-error">⚠ <%= request.getAttribute("marksError") %></span>
                <% } %>
            </div>

            <div class="form-group">
                <label for="date">Exam Date</label>
                <input type="date" id="date" name="date"
                       value="<%= request.getAttribute("oldDate") != null ? request.getAttribute("oldDate") : "" %>">
            </div>
        </div>

        <button type="submit" class="btn btn-primary btn-full">Add Student →</button>
    </form>

    <div class="nav-bar">
        <a href="index.jsp" class="btn btn-outline">🏠 Home</a>
    </div>
</div>
</body>
</html>
