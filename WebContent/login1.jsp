<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<html>
<head>
<meta charset="UTF-8">
<title>Homepage</title>
</head>
<body>
<h2>Create User</h2>
    <form action="/Test/reglog?action=create" method="POST">
        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom">
        
        <label for="prenom">Prenom :</label>
        <input type="text" id="prenom" name="prenom">
        
        <label for="pass">Pass :</label>
        <input type="text" id="pass" name="pass">

        <label for="email">Email :</label>
        <input type="text" id="email" name="email">
        
        <input type="hidden" name="role" value="CLIENT">
		

        <input type="submit">
    </form>
    <form action="/Test/reglog?action=login" method="POST">
		
        <label for="email">Email :</label>
        <input type="text" id="email" name="email">
		
        <label for="pass">Pass :</label>
        <input type="text" id="pass" name="pass">

        <input type="submit">
    </form>

</body>
</html>