<html>
<head>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
<div class="container">

<h2>Add Student Mark</h2>

<form action="addMark" method="post">
    <input type="text" name="id" placeholder="Student ID" required>
    <input type="text" name="name" placeholder="Student Name" required>
    <input type="text" name="subject" placeholder="Subject" required>
    <input type="text" name="marks" placeholder="Marks" required>
    <input type="date" name="date" required>

    <button type="submit">Add Mark</button>
</form>

<a href="index.jsp" class="back"> Back</a>

</div>
</body>
</html>