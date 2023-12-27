<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Parser</title>
    <style>
        body {
            font-family: Helvetica, sans-serif;
            text-align: center;
        }
    </style>
</head>
<body>
<h2><%= "Choose parser type!" %></h2>
<br/>
    <form action="hello-servlet">
        <input type="hidden" name="currentPage" value="1">
        <select name="parserType">
            <option value="1" selected>DOM</option>
            <option value="2">SAX</option>
            <option value="3">StAX</option>
        </select>
        <button type="submit">Parse</button>
    </form>
</body>
</html>