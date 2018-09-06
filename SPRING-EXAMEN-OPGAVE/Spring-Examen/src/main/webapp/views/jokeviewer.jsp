<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.util.Scanner" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Grade a student</title>
</head>
<body>

<%
    String firstName = request.getParameter("firstname");
    String lastName = request.getParameter("lastname");

    String url = "http://api.icndb.com/jokes/random?firstName=" + firstName + "&lastName=" + lastName;
    String joke = new Scanner(new URL("http://www.google.com").openStream(), "UTF-8").useDelimiter("\\A").next();

out.println(joke);
%>


</body>
</html>