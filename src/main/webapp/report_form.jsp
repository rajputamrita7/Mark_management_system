<html>
<head>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
<div class="container">

<h2>Reports</h2>

<form action="report" method="post">

<select name="type" required>
    <option value="">Select Report</option>
    <option value="marks">Marks Above</option>
    <option value="subject">Subject</option>
    <option value="top">Top N</option>
</select>

<input type="text" name="value" placeholder="Enter value" required>

<button type="submit">Generate</button>

</form>

<a href="index.jsp" class="back"> Back</a>

</div>
</body>
</html>