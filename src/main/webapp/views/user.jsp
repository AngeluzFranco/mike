<%--
  Created by IntelliJ IDEA.
  User: angry
  Date: 22/08/2023
  Time: 08:47 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User</title>
    <jsp:include page="/layouts/head.jsp"/>

</head>
<body>
<section class="vh-100" style="background-color: #508bfc;">

<nav class="navbar bg-dark border-bottom border-body" data-bs-theme="whith">
    <div class="container-fluid">
        <form action="/api/auth/logout">
            <button class="btn btn-success">Cerrar sesión</button>
        </form>
    </div>
</nav>


<div class="row">
    <div class="col text-center mt-5">
        <h2>Incidencias</h2>
        <div class="card">
            <div class="card-header">
                <div class="row">

                    <div class="col text-end">
                        <a href="/incidencia/view-create" class="btn btn-outline-success btn-sm">
                            REGISTRAR
                        </a>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <table class="table table-stripped">
                    <thead>
                    <th>#</th>
                    <th>Titulo</th>
                    <th>Descripcion</th>
                    <th>Tipo</th>
                    <th>Status</th>
                    </thead>
                    <tbody>
                    <s:forEach var="incidencia" items="${incidencias}" varStatus="s">
                        <tr>
                            <td>
                                <s:out value="${s.count}"/>
                            </td>
                            <td>
                                <s:out value="${incidencia.titulo}"/>
                            </td>
                            <td>
                                <s:out value="${incidencia.descripcion}"/>
                            </td>
                            <td>
                                <s:out value="${incidencia.tipo}"/>
                            </td>
                            <td>
                                <s:out value="${incidencia.status}"/>
                            </td>
                        </tr>
                    </s:forEach>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</section>

<jsp:include page="/layouts/footer.jsp"/>
</body>
</html>
