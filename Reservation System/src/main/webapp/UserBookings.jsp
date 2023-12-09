<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Bookings</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #333;
            color: #fff;
            text-align: center;
            padding: 20px;
        }

        h1 {
            margin: 0;
        }

        main {
            max-width: 90%;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f9f9f9;
        }

        .cancellation-button {
            background-color: #f44336;
            color: #fff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
        }

        .cancellation-button:hover {
            background-color: #d32f2f;
        }

        footer {
            text-align: center;
            padding: 10px;
            background-color: #333;
            color: #fff;
        }

        @media screen and (max-width: 768px) {
            main {
                padding: 10px;
            }
        }
    </style>
</head>
<body>
    <header>
        <h1>Your Bookings</h1>
    </header>

    <main>
        <table>
            <tr>
                <th>Origin</th>
                <th>Destination</th>
                <th>Class</th>
                <th>Date of Journey</th>
                <th>No. of Passengers</th>
            </tr>
            <c:forEach var="booking" items="${bookings}">
                <tr>
                    <td><c:out value="${booking.origin}" /></td>
                    <td><c:out value="${booking.dest}" /></td>
                    <td><c:out value="${booking.jclass}" /></td>
                    <td><c:out value="${booking.jdate}" /></td>
                    <td><c:out value="${booking.no_of_pass}" /></td>
                </tr>
            </c:forEach>
        </table>
    </main>

    <footer>
        &copy; 2023 Your Company
    </footer>
</body>
</html>
