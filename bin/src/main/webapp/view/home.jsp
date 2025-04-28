<!DOCTYPE html>
<html lang="en">
<head>
	
	<link rel="stylesheet" type="text/css"        href="<%=request.getContextPath() %>/css/homepage.css">
	<script src="<%=request.getContextPath() %>/js/homepage.js"></script>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vendor Management</title>

</head>
<body>

    <div class="dropdown">
        <button class="dropbtn">Menu</button>
        <div class="dropdown-content">
            <a href="<%=request.getContextPath() %>/settings">Settings</a>
            <a href="<%=request.getContextPath() %>/login">Logout</a>
        </div>
    </div>

    <div class="container">
		
        <h1>Vendor Management</h1>
        <div class="controls">
            <input
                type="text"
                id="search"
                placeholder="Search by name..."
                oninput="fetchVendors()"
            />
            <select id="sortBy" onchange="updatePlaceholder(); fetchVendors();">
                <option value="name">Name</option>
                <option value="email">Email</option>
                <option value="phone">Phone</option>
                <option value="address">Address</option>
            </select>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Address</th>
                </tr>
            </thead>
            <tbody id="vendorTable">
                <tr>
                    <td colspan="4">Loading...</td>
                </tr>
            </tbody>
        </table>
    </div>

   
</body>
</html>
