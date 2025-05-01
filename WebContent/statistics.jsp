<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Statistiques de la publication</title>
</head>
<body>
<h1>Statistiques de la publication Instagram</h1>

<c:choose>
    <c:when test="${not empty stats}">
        <p><strong>Media ID :</strong> ${stats.mediaId}</p>
        <p><strong>Engagement :</strong> ${stats.engagement}</p>
        <p><strong>Impressions :</strong> ${stats.impressions}</p>
        <p><strong>Reach :</strong> ${stats.reach}</p>
        <p><strong>Saved :</strong> ${stats.saved}</p>
    </c:when>
    <c:otherwise>
        <p>Aucune statistique disponible.</p>
    </c:otherwise>
</c:choose>
</body>
</html>
