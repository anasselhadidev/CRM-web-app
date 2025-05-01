<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- allInstagramStats.jsp -->
<html>
<head>
    <title>Statistiques des publications Instagram</title>
</head>
<body>
    <h1>Statistiques de toutes les publications Instagram</h1>
    <table border="1">
        <tr>
            <th>Publication ID</th>
            <th>Impressions</th>
            <th>Reach</th>
            <th>Likes</th>
            <th>Comments</th>
            <th>Saved</th>
        </tr>
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
    </table>
</body>
</html>
