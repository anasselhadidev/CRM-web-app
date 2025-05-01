<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="réclamation.ReclamationBean" %>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Réclamations Client</title>
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                <div class="sidebar-brand-icon">
                    <i class="fas fa-user"></i>
                </div>
                <div class="sidebar-brand-text mx-3">Client</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Menu -->
            <li class="nav-item">
                <a class="nav-link" href="ReclamationClientServlet">Voir Réclamations</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-danger" href="login.jsp">Déconnexion</a>
            </li>
        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Client</span>
                                <img class="img-profile rounded-circle" src="img/undraw_profile.svg">
                            </a>
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                                <a class="dropdown-item text-danger" href="login.jsp">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Déconnexion
                                </a>
                            </div>
                        </li>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Page Content -->
                <div class="container-fluid">
                    <h2 class="text-center">Liste des Réclamations</h2>
                    <table class="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Sujet</th>
                                <th>Description</th>
                                <th>Date</th>
                                <th>Statut</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                            List<ReclamationBean> reclamations = (List<ReclamationBean>) request.getAttribute("reclamations");
                            if (reclamations != null) {
                                for (ReclamationBean reclamation : reclamations) { 
                            %>
                            <tr>
                                <td><%= reclamation.getIdReclamation() %></td>
                                <td><%= reclamation.getSujet() %></td>
                                <td><%= reclamation.getDescription() %></td>
                                <td><%= reclamation.getDateReclamation() %></td>
                                <td><%= reclamation.getStatut() %></td>
                            </tr>
                            <% 
                                }
                            } else { 
                            %>
                            <tr>
                                <td colspan="5" class="text-center">Aucune réclamation trouvée</td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>

                    <!-- Formulaire de soumission de réclamation -->
                    <h3 class="mt-5">Soumettre une Réclamation</h3>
                    <form action="ReclamationClientServlet" method="post">
                        <div class="form-group">
                            <label for="sujet">Sujet</label>
                            <input type="text" id="sujet" name="sujet" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="description">Description</label>
                            <textarea id="description" name="description" class="form-control" rows="4" required></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary mt-3">Envoyer</button>
                    </form>
                </div>
                <!-- End Page Content -->
            </div>
            <!-- End Main Content -->
        </div>
        <!-- End Content Wrapper -->
    </div>
    <!-- End Page Wrapper -->

    <!-- Scripts -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="js/sb-admin-2.min.js"></script>
</body>
</html>
