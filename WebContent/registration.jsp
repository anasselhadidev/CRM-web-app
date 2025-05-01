<!DOCTYPE html>
<html lang="en">
<head>
		
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up Form</title>

    <link rel="stylesheet" href="css/style.css">
    <style>
    /* Style général pour les champs de saisie */
.form-group input,
.form-group select {
    width: 100%;
    padding: 10px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    margin-bottom: 20px;
    outline: none;
}

/* Ajouter un effet de focus cohérent */
.form-group input:focus,
.form-group select:focus {
    border-color: #6c63ff;
    box-shadow: 0 0 5px rgba(108, 99, 255, 0.5);
}

/* Icônes alignées avec le champ */
.form-group label {
    position: absolute;
    margin-left: 10px;
    margin-top: 10px;
    color: #999;
    font-size: 20px;
}
    
    </style>
</head>
<body>

    <div class="main">
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                	<div class="signin-image">
						<figure>
							<img src="images/signin-image.jpg" alt="sing up image">
						</figure>
					</div>
                    <div class="signup-form">
                        <h2 class="form-title">Sign Up</h2>
                        <form method="POST" action="/CRM/reglog?action=create" class="register-form" id="register-form">
                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="nom" id="nom" placeholder="Your Second Name" />
                            </div>
                            <div class="form-group">
                                <label for="prenom"><i class="zmdi zmdi-email"></i></label>
                                <input type="text" name="prenom" id="prenom" placeholder="Your First Name" />
                            </div>
                            <div class="form-group">
                                <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="pass" id="pass" placeholder="Password" />
                            </div>
                            <div class="form-group">
                                <label for="email"><i class="zmdi zmdi-lock"></i></label>
                                <input type="text" name="email" id="email" placeholder="Your Email" />
                            </div>
                            <div class="form-group">
    						<label for="role"><i class="zmdi zmdi-lock"></i></label>
    						<select name="role" id="role">
    						
        					<option value="" disabled selected>Select your Role</option>
        					<option value="agentsupport">Agent Support</option>
        					<option value="Client">Client</option>
        					<option value="respmarketing">Responsable Marketing</option>
        					<option value="repcommercial">Représentant Commercial</option>
    						</select>
							</div>
                                                  
                            <div class="form-group">
                                <label for="contact"><i class="zmdi zmdi-phone"></i></label>
                                <input type="text" name="contact" id="contact" placeholder="Your Contact" />
                            </div>
                            <div class="form-group">
                                <label for="adresse"><i class="zmdi zmdi-phone"></i></label>
                                <input type="text" name="adresse" id="adresse" placeholder="Your adresse" />
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="signup" id="signup" class="form-submit" value="Register" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div>

</body>
</html>
