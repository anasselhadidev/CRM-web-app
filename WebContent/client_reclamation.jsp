<%@ page import="java.util.List" %>
<%@ page import="réclamation.ResponseBean" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Client Réclamation</title>

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
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="#">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">Portail Client</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Navigation -->
            <li class="nav-item">
                <a class="nav-link" href="client_reclamation.jsp?action=create">
                    <i class="fas fa-fw fa-pen"></i>
                    <span>Faire une Réclamation</span></a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="ReclamationServlet?action=viewResponses">
                    <i class="fas fa-fw fa-envelope"></i>
                    <span>Voir les Réponses</span></a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="login.jsp">
                    <i class="fas fa-fw fa-sign-out-alt"></i>
                    <span>Déconnexion</span></a>
            </li>
        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
            <!-- Main Content -->
            <div id="content">
                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <%-- JSP Dynamic Content Here --%>
                    <%
                        String action = request.getParameter("action");
                        if (action == null || action.equals("create")) {
                    %>
                    <!-- Formulaire pour soumettre une réclamation -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Faire une Réclamation</h6>
                        </div>
                        <div class="card-body">
                            <form action="ReclamationServlet" method="post">
                                <div class="mb-3">
                                    <label for="sujet" class="form-label">Sujet</label>
                                    <input type="text" class="form-control" id="sujet" name="sujet" required>
                                </div>
                                <div class="mb-3">
                                    <label for="description" class="form-label">Description</label>
                                    <textarea class="form-control" id="description" name="description" rows="4" required></textarea>
                                </div>
                                <button type="submit" class="btn btn-primary" name="action" value="create">Soumettre</button>
                            </form>
                        </div>
                    </div>
                    <%
                        } else if (action.equals("viewResponses")) {
                            List<ResponseBean> responses = (List<ResponseBean>) request.getAttribute("responses");
                    %>
                    <!-- Table des réponses -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Réponses de l'Agence Support</h6>
                        </div>
                        <div class="card-body">
                            <% if (responses != null && !responses.isEmpty()) { %>
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>Nom du Client</th>
                                        <th>Email</th>
                                        <th>Sujet</th>
                                        <th>Description</th>
                                        <th>Réponse</th>
                                        <th>Date de Réponse</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for (ResponseBean res : responses) { %>
                                    <tr>
                                        <td><%= res.getNomClient() %></td>
                                        <td><%= res.getEmailClient() %></td>
                                        <td><%= res.getSujet() %></td>
                                        <td><%= res.getDescription() %></td>
                                        <td><%= res.getResponseText() %></td>
                                        <td><%= res.getResponseDate() %></td>
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                            <% } else { %>
                            <p class="text-center text-muted">Aucune réponse disponible pour le moment.</p>
                            <% } %>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- End of Main Content -->
        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>
</body>

</html>
