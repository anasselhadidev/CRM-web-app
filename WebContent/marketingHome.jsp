<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Statistiques des publications Instagram</title>
    <!-- Custom fonts and styles -->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <!-- Chart.js -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
                <a class="nav-link" href="RmPage.jsp">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Dashboard</span></a>
            </li>
			
			<li class="nav-item">
                <a class="nav-link" href="/Test/SondageServlet">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Accéder aux statistiques</span></a>
            </li>
            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Interface
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
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
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Responsable Marketing</span>
                                <img class="img-profile rounded-circle" src="img/undraw_profile.svg">
                            </a>
                        </li>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Page Content -->
                <div class="container-fluid">
                    <h1 class="h3 mb-4 text-gray-800">Statistiques des publications Instagram</h1>

                    <!-- Graphique -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Graphique des statistiques</h6>
                        </div>
                        <div class="card-body">
                            <canvas id="statsChart" width="400" height="200"></canvas>
                        </div>
                    </div>

                    <!-- Tableau -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Détails des statistiques</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>Publication ID</th>
                                            <th>Impressions</th>
                                            <th>Reach</th>
                                            <th>Likes</th>
                                            <th>Comments</th>
                                            <th>Saved</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="stats" items="${statsList}">
                                            <tr>
                                                <td>${stats.postId}</td>
                                                <td>${stats.impressions}</td>
                                                <td>${stats.reach}</td>
                                                <td>${stats.likes}</td>
                                                <td>${stats.comments}</td>
                                                <td>${stats.saved}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
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

    <!-- Script pour le graphique -->
    <script>
        // Préparer les données pour chaque publication
        const statsData = {
            labels: ['Likes', 'Reach', 'Impressions', 'Comments', 'Saved'], // Catégories sur l'axe des abscisses
            datasets: [
                <c:forEach var="stats" items="${statsList}">
                {
                    label: 'Publication ${stats.postId}', // Identifiant de la publication
                    data: [${stats.likes}, ${stats.reach}, ${stats.impressions}, ${stats.comments}, ${stats.saved}], // Valeurs
                    borderColor: `rgba(${Math.floor(Math.random() * 255)}, ${Math.floor(Math.random() * 255)}, ${Math.floor(Math.random() * 255)}, 1)`,
                    backgroundColor: 'transparent',
                    tension: 0.3, // Lignes courbées
                    fill: false, // Pas de remplissage
                },
                </c:forEach>
            ]
        };

        // Configuration du graphique
        const config = {
            type: 'line', // Graphique en ligne
            data: statsData,
            options: {
                responsive: true,
                plugins: {
                    legend: { position: 'top' },
                    title: { display: true, text: 'Statistiques des publications Instagram' },
                },
                scales: {
                    x: { title: { display: true, text: 'Catégories' } },
                    y: { title: { display: true, text: 'Valeurs' } },
                },
            },
        };

        // Dessiner le graphique
        const statsChart = new Chart(document.getElementById('statsChart'), config);
    </script>

</body>
</html>