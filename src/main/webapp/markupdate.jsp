<html>
<head>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
<div class="container">

<h2>Update Student Mark</h2>

<form action="updateMark" method="post">
    <input type="text" name="id" placeholder="Student ID" required>
    <input type="text" name="name" placeholder="New Name" required>
    <input type="text" name="subject" placeholder="Subject" required>
    <input type="text" name="marks" placeholder="New Marks" required>
    <input type="date" name="date" required>

    <button type="submit">Update Mark</button>
</form>

<a href="index.jsp" class="back"> Back</a>

</div>
</body>
</html>