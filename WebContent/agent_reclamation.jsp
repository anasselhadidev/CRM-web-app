<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="réclamation.ReclamationBean" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Réclamations Reçues</title>
    <!-- Custom fonts and styles -->
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
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">Agent Panel</div>
            </a>
            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Menu Items -->
            <li class="nav-item">
                <a class="nav-link" href="ReclamationAgentServlet">
                    <i class="fas fa-list"></i>
                    <span>Liste des réclamations</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="ResponsesAgentServlet">
                    <i class="fas fa-reply"></i>
                    <span>Liste des réponses</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-danger" href="login.jsp">
                    <i class="fas fa-sign-out-alt"></i>
                    <span>Déconnexion</span>
                </a>
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
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Agent</span>
                                <img class="img-profile rounded-circle" src="img/undraw_profile.svg">
                            </a>
                        </li>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Page Content -->
                <div class="container-fluid">
                    <h1 class="h3 mb-4 text-gray-800">Liste des Réclamations</h1>
                    <table class="table table-bordered table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>ID</th>
                                <th>Nom</th>
                                <th>Email</th>
                                <th>Sujet</th>
                                <th>Description</th>
                                <th>Date</th>
                                <th>Statut</th>
                                <th style="width: 20%;">Actions</th>
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
                                <td><%= reclamation.getNomClient() %></td>
                                <td><%= reclamation.getEmailClient() %></td>
                                <td><%= reclamation.getSujet() %></td>
                                <td><%= reclamation.getDescription() %></td>
                                <td><%= reclamation.getDateReclamation() %></td>
                                <td><%= reclamation.getStatut() %></td>
                                <td>
                                    <form action="ReclamationAgentServlet" method="post">
                                        <input type="hidden" name="idReclamation" value="<%= reclamation.getIdReclamation() %>">
                                        <textarea class="form-control mb-2" name="responseText" placeholder="Votre réponse" required></textarea>
                                        <button type="submit" class="btn btn-success w-100" name="action" value="respond">Répondre</button>
                                    </form>
                                </td>
                            </tr>
                            <% 
                                }
                            } else { 
                            %>
                            <tr>
                                <td colspan="8" class="text-center">Aucune réclamation trouvée</td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
                <!-- End of Page Content -->
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

    <!-- Scripts -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <script src="js/sb-admin-2.min.js"></script>

</body>
</html>

