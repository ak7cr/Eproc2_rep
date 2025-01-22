<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of Tenders</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/list.css">
    <script src="<%=request.getContextPath() %>/js/list.js" defer></script>
</head>
<body>
    <div class="container">
        <h1>List of Tenders</h1>
        <table>
            <thead>
                <tr>
                    <th>Sl No</th>
                    <th>Tender Description</th>
                    <th>Reference No</th>
                    <th>Department</th>
                    <th>End Date</th>
                    <th>Time Left</th>
                </tr>
            </thead>
            <tbody id="listTable">
       
            </tbody>
        </table>
    </div>
</body>
</html>