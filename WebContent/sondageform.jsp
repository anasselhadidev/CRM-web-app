<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<%
HttpSession sessionver = request.getSession(false);
if (sessionver == null){
    System.out.println("SESSION NULL");
    response.sendRedirect("login.jsp");
} else if("RESPMARKETING".equals(sessionver.getAttribute("role"))){
    System.out.println("RESPMARKETING ROLE ENTERS SONDAGEFORM.JSP");
%>
<html lang="en">
<style>
.card-header h6 {
    font-size: 1.25rem; /* Larger header size */
    color: #0056b3; /* Stylish primary color */
    font-weight: bold; /* Bold text */
    text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1); /* Subtle shadow */
}

.card-body h2, .card-body h3 {
    font-family: 'Arial', sans-serif; /* Change font for a modern look */
    color: #333; /* Darker text for readability */
    margin-bottom: 10px;
    border-bottom: 2px solid #007bff; /* Underline effect */
    padding-bottom: 5px; /* Space between text and underline */
}

.card-body label {
    display: block;
    font-size: 1rem;
    font-weight: 500; /* Medium weight for labels */
    margin-bottom: 5px;
    color: #555; /* Slightly lighter color */
}

input[type="checkbox"] {
    width: 20px; /* Increase the size of the checkbox */
    height: 20px; /* Increase the size of the checkbox */
    cursor: pointer; /* Pointer cursor on hover */
    accent-color: #007bff; /* Change the color of the checkbox */
    border-radius: 5px; /* Optional: rounded corners */
    transition: transform 0.2s; /* Smooth scale effect */
}

input[type="checkbox"]:hover {
    transform: scale(1.1); /* Enlarge checkbox slightly on hover */
}

input[type="checkbox"]:checked {
    background-color: #007bff; /* Color of the checkbox when checked */
    border: 2px solid #007bff; /* Border color when checked */
}


.card-body input[type="text"] {
    width: 100%;
    padding: 10px;
    border-radius: 4px; /* Rounded corners */
    border: 1px solid #ddd; /* Light border */
    outline: none; /* Remove default outline */
    font-size: 1rem;
    transition: border-color 0.3s;
}

.card-body input[type="text"]:focus {
    border-color: #007bff; /* Change border color on focus */
    box-shadow: 0 0 5px rgba(0, 123, 255, 0.3); /* Subtle focus effect */
}

.card-body p {
    color: #007bff; /* Color for important text */
    font-size: 0.95rem; /* Slightly smaller text */
}

.table thead tr {
    background-color: #f8f9fa; /* Light background */
    color: #0056b3; /* Header text color */
}

.table tbody tr:nth-child(even) {
    background-color: #f2f2f2; /* Zebra striping */
}

.table tbody tr:hover {
    background-color: #e9ecef; /* Hover effect */
}

.btn-stylish {
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    padding: 10px 20px;
    font-size: 1rem;
    cursor: pointer;
    transition: background-color 0.3s, transform 0.2s;
}

.btn-stylish:hover {
    background-color: #0056b3;
    transform: translateY(-2px);
}

.btn-stylish:active {
    background-color: #003f7f;
    transform: translateY(1px);
}

.btn-stylish:focus {
    outline: none;
}
.table th {
    background-color: #007bff; /* Header background color */
    color: #ffffff; /* White text for contrast */
    text-transform: uppercase; /* Uppercase text */
    font-weight: bold; /* Bold header text */
}

.table td {
    color: #333; /* Darker color for table data */
    font-size: 0.95rem; /* Slightly smaller text */
}

.table tbody tr:nth-child(even) {
    background-color: #f9f9f9; /* Light gray for even rows */
}

.table tbody tr:nth-child(odd) {
    background-color: #ffffff; /* White for odd rows */
}

.table tbody tr:hover {
    background-color: #e9ecef; /* Light gray hover effect */
    color: #000; /* Dark color on hover for contrast */
}

.table tbody td {
    padding: 10px; /* Padding for table cells */
    border: 1px solid #ddd; /* Light border */
}

.table th, .table td {
    text-align: center; /* Center align header and data */
}

.table th:first-child, .table td:first-child {
    text-align: left; /* Align first column text to the left */
}

</style>
<html lang="en">
<head>
    <title>Sondage : choix de nombre de reponses</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Responsable Marketing Dashboard</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="RmPage.jsp">
                <div class="sidebar-brand-text mx-3">Responsable Marketing <sup></sup></div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="/Test/SondageServlet">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Dashboard</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Interface
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
                    <span>Sondages</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="/Test/SondageServlet?action=creersondage">Créer un Sondage</a>
                        <a class="collapse-item" href="/Test/SondageServlet?action=voirsondages">Voir les Sondages Existants</a>
                    </div>
                </div>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            
            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

            
        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <!-- Topbar Search -->
                    

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <!-- Nav Item - Alerts -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            
                            <!-- Dropdown - Alerts -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="alertsDropdown">
                                <h6 class="dropdown-header">
                                    Alerts Center
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-primary">
                                            <i class="fas fa-file-alt text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 12, 2019</div>
                                        <span class="font-weight-bold">A new monthly report is ready to download!</span>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-success">
                                            <i class="fas fa-donate text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 7, 2019</div>
                                        $290.29 has been deposited into your account!
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-warning">
                                            <i class="fas fa-exclamation-triangle text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 2, 2019</div>
                                        Spending Alert: We've noticed unusually high spending for your account.
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
                            </div>
                        </li>

                        <!-- Nav Item - Messages -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="messagesDropdown">
                                <h6 class="dropdown-header">
                                    Message Center
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/undraw_profile_1.svg" alt="...">
                                        <div class="status-indicator bg-success"></div>
                                    </div>
                                    <div class="font-weight-bold">
                                        <div class="text-truncate">Hi there! I am wondering if you can help me with a
                                            problem I've been having.</div>
                                        <div class="small text-gray-500">Emily Fowler · 58m</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/undraw_profile_2.svg" alt="...">
                                        <div class="status-indicator"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">I have the photos that you ordered last month, how
                                            would you like them sent to you?</div>
                                        <div class="small text-gray-500">Jae Chun · 1d</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/undraw_profile_3.svg" alt="...">
                                        <div class="status-indicator bg-warning"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">Last month's report looks great, I am very happy with
                                            the progress so far, keep up the good work!</div>
                                        <div class="small text-gray-500">Morgan Alvarez · 2d</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="https://source.unsplash.com/Mv9hjnEUHR4/60x60" alt="...">
                                        <div class="status-indicator bg-success"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">Am I a good boy? The reason I ask is because someone
                                            told me that people say this to all dogs, even if they aren't good...</div>
                                        <div class="small text-gray-500">Chicken the Dog · 2w</div>
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
                            </div>
                        </li>

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%=sessionver.getAttribute("Nom")%>  <%=sessionver.getAttribute("Prenom")%> </span>
                                <img class="img-profile rounded-circle" src="img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Settings
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Activity Log
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Créer un Sondage</h1>
                    </div>

                    
                    

                    <!-- Content Row -->
<div class="row">
    <!-- Area Chart -->
    <div class="col-xl-8 col-lg-7" style="
    margin-left: 200px;">
        <div class="card shadow mb-4" style="margin: 20px auto; max-width: 800px;">
            <!-- Card Header -->
            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                <h6 class="m-0 font-weight-bold text-primary">Choisir le nombre de réponses pour chaque question :</h6>
            </div>

            <!-- Card Body -->
            <form action="/Test/SondageServlet?action=createsondage" method="POST">
                <div class="card-body">
                    <h2>Description</h2>
                    <label for="description">Description :</label>
                    <input type="text" id="description" name="description" placeholder="Saisissez votre description du sondage" style="width: 100%; margin-bottom: 15px;"><br>
                    <input type="hidden" name="numquestions" value="${numquestions}">
                   

                    <h2>Questions</h2>
                    <div id="questions" style="color: black;">
                        <c:forEach var="i" begin="0" end="${fn:length(responses) - 1}" varStatus="status">
                            <label for="question${status.index}">Question ${status.index + 1} :</label><br>
                            <input type="text" id="question${status.index}" name="questions[${status.index}].questionText" placeholder="Saisissez votre question" style="width: 100%; margin-bottom: 10px;"><br>
                            <input type="hidden" name="numResponses[${status.index}]" value="${responses[status.index] != null ? responses[status.index] : 0}">
                          

                            <h3>Réponses</h3>
                            <c:forEach var="j" begin="0" end="${responses[status.index] - 1}">
                                <label for="reponse${status.index}_${j}">Réponse ${j + 1} :</label><br>
                                <input type="text" id="reponse${status.index}_${j}" name="reponses[${status.index}][${j}].reponseText" placeholder="Saisissez votre réponse" style="width: 100%; margin-bottom: 10px;"><br>
                            </c:forEach>
                        </c:forEach>
                    </div>
                </div>

                <!-- Users Table in a Separate Card -->
                <div class="card shadow mb-4" style="margin: 20px auto; max-width: 800px;">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Choisir les destinataires</h6>
                    </div>
                    <div class="card-body">
                        <table class="table" border="1" style="width: 100%; border-collapse: collapse; margin-top: 20px;">
    <thead>
        <tr>
            <th>Selectionner</th>
            <th>Nom</th>
            <th>Prenom</th>
            <th>Email</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>
                    <input type="checkbox" name="emails" value="${user.email}">
                </td>
                <td>${user.nom}</td>
                <td>${user.prenom}</td>
                <td>${user.email}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

                    </div>
                </div>

                <!-- Submit Button -->
                <div style="text-align: center; margin-top: 20px;">
                    <button type="submit" class="btn-stylish">Send Email</button>
                </div>
            </form>
        </div>
    </div>
</div>



                        <!-- Pie Chart -->
                        
                    </div>


                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                     <a class="btn btn-primary" href="/Test/reglog?action=logout">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>




</body>


<%}
else {
	System.out.println(sessionver.getAttribute("role") + " tried to enter SONDAGEFORM.JSP");
    response.sendRedirect("login.jsp");
} %>

