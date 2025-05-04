<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vendor Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            padding: 50px;
        }
        .container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            margin: auto;
        }
        h1 {
            color: #333;
        }
        input {
            width: 90%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background-color:blue;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
        }
        button:hover {
            background-color: #218838;
        }

        .dob {
            text-align: left;
            margin: 10px 0;
            font-size: 12px;
            color: #333;
        }
    </style>
    <script src="<%= request.getContextPath() %>/js/employee.js" defer></script>

</head>
<body>
    <div class="container">
      <h1>Vendor Form</h1>
  
      <div id="responseMessage" style="margin-bottom:10px;color:red;"></div>
  
      <form id="employeeForm" onsubmit="event.preventDefault(); submitEmployee();">
        <input id="name"       name="name"       type="text"     placeholder="Enter your name"      required /><br/>
        <input id="email"      name="email"      type="email"    placeholder="Enter your email"     required /><br/>
        <input id="phone"      name="phone"      type="text"     placeholder="Enter your phone"     required /><br/>
        <input id="city"       name="city"       type="text"     placeholder="Enter your city"      required /><br/>
        <input id="state"      name="state"      type="text"     placeholder="Enter your state"     required /><br/>
        <input id="department" name="department" type="text"     placeholder="Enter your department" required /><br/>
        <input id="dob"        name="dob"        type="date"     placeholder="Enter your DOB"        required /><br/>
        <button type="submit">Submit Now</button>
      </form>
    </div>
  </body>
</html>