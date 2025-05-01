<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>CRM Login Page</title>

    <!-- Custom fonts and styles -->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body class="bg-gradient-primary d-flex align-items-center justify-content-center" style="height: 100vh;">

    <div class="container">
        <div class="card o-hidden border-0 shadow-lg my-5 mx-auto" style="max-width: 500px;">
            <div class="card-body p-0">
                <div class="p-5">
                    <div class="text-center">
                        <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                    </div>
                    <form class="user" method="POST" action="/CRM/reglog?action=login">
                        <div class="form-group">
                            <input type="email" class="form-control form-control-user" id="email" name="email"
                                placeholder="Enter Email Address..." required>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control form-control-user" id="password" name="pass"
                                placeholder="Password" required>
                        </div>
                        <div class="form-group">
                            <div class="custom-control custom-checkbox small">
                                <input type="checkbox" class="custom-control-input" id="remember-me">
                                <label class="custom-control-label" for="remember-me">Remember Me</label>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary btn-user btn-block">
                            Login
                        </button>
                    </form>
                    <hr>
                    <div class="text-center">
                        <a class="small" href="forgot-password.jsp">Forgot Password?</a>
                    </div>
                    <div class="text-center">
                        <a class="small" href="registration.jsp">Create an Account!</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <script src="js/sb-admin-2.min.js"></script>

</body>

</html>
