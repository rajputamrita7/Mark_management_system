<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <p class="page-title">❌ Something Went Wrong</p>
    <p class="page-subtitle">An unexpected error occurred while processing your request.</p>

    <div class="alert alert-error">
        <div>
            <strong>Error Details:</strong><br>
            <%= request.getAttribute("error") != null
                ? request.getAttribute("error")
                : "An unknown error occurred." %>
        </div>
    </div>

    <div class="nav-bar">
        <a href="javascript:history.back()" class="btn btn-outline">⬅ Go Back</a>
        <a href="index.jsp" class="btn btn-primary">🏠 Home</a>
    </div>
</div>
</body>
</html>
