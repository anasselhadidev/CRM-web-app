<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %> 
<%@ page import="java.util.Arrays" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Questions and Responses</title>
    <style>
        /* Background gradient for the entire page */
        body {
            background: linear-gradient(to right, #ff7e5f, #feb47b);
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
        }
        
        /* Center the content in the page */
        .container {
            max-width: 800px;
            margin: 50px auto;
            padding: 30px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }

        /* Header styling */
        h1 {
            text-align: center;
            color: #333;
        }

        /* Question container styling */
        .question-container {
            margin-bottom: 20px;
        }
        .question-container label {
            font-weight: bold;
            color: #333;
        }

        .responses-container {
            margin-left: 20px;
        }

        /* Radio button styling */
        .form-check {
            margin-bottom: 10px;
        }

        .form-check-label {
            color: #333;
        }

        .form-check-input {
            border-color: #feb47b;
            background-color: #ff7e5f;
        }

        .form-check-input:checked {
            background-color: #ffcc00;
        }

        /* Button styling */
        .btn-primary {
            background-color: #ff7e5f;
            border-color: #feb47b;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            text-align: center;
            width: 100%;
            cursor: pointer;
        }

        .btn-primary:hover {
            background-color: #feb47b;
            border-color: #ff7e5f;
        }

        .text-center {
            text-align: center;
        }

        /* Responsive behavior */
        @media (max-width: 600px) {
            .container {
                padding: 15px;
            }

            .btn-primary {
                width: 100%;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Questions and Responses</h1>

        <form action="/Test/SondageServlet?action=saveresponse" method="post">
            <input type="hidden" name="token" value="${token}">
            
            <% 
                List<String[]> questionResponses = (List<String[]>) request.getAttribute("questionReponses");
                if (questionResponses != null && !questionResponses.isEmpty()) {
                    for (String[] qr : questionResponses) {
                        String idQuestion = qr[0]; 
                        String question = qr[1];  
                        String responses = qr[2]; 
                        String[] responseArray = responses.split(";");
            %>
            <div class="question-container">
                <label for="<%= idQuestion %>"><%= question %></label>
                <div class="responses-container">
                    <% for (String resp : responseArray) { %>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="<%= idQuestion %>" value="<%= resp.trim() %>" id="<%= idQuestion + resp.trim() %>">
                        <label class="form-check-label" for="<%= idQuestion + resp.trim() %>">
                            <%= resp.trim() %>
                        </label>
                    </div>
                    <% } %>
                </div>
            </div>
            <% 
                    }
            %>
            <div class="text-center">
                <button type="submit" class="btn-primary">Submit Responses</button>
            </div>
            <% } else { %>
            <p class="text-center">No questions or responses found.</p>
            <% } %>
        </form>
    </div>
</body>
</html>
