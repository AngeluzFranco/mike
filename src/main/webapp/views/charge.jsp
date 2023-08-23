<%--
  Created by IntelliJ IDEA.
  User: aldri
  Date: 14/08/2023
  Time: 01:50 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student</title>
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

<section class="container">
<div class="row">
    <div class="col text-center mt-5">
        <p>CHARGE</p>
        <div class="card">
            <div class="card-header">
                <div class="row">
                </div>
            </div>
            <div class="card-body">
                <table class="table table-stripped">
                    <thead>
                    <th>#</th>
                    <th>Titulo</th>
                    <th>Descripcion</th>
                    <th>Tipo</th>
                    <th>Estado</th>
                    <th>    </th>
                    </thead>
                    <tbody>
                    <s:forEach var="incidencias" items="${incidencias}" varStatus="s">
                       <tr>
                            <td>
                                <s:out value="${s.count}"/>
                            </td>
                            <td>
                                <s:out value="${incidencias.titulo}"/>
                            </td>
                            <td>
                                <s:out value="${incidencias.descripcion}"/>
                            </td>
                            <td>
                                <s:out value="${incidencias.tipo}"/>
                            </td>
                            <td>
                                <s:out value="${incidencias.status}"/>
                            </td>
                           <td>
                           <div class="row">
                            <div class="col">
                                <form action="/charge/active" method="post">
                                        <button type="submit" class="btn btn-outline-success btn-sm">
                                            <input hidden name="id" value="${incidencias.id_incidencia}">
                                            <i data-feather="edit"></i> ACTIVAR
                                        </button>
                                </form>
                                <form action="/charge/inactiva" method="post">
                                    <button type="submit" class="btn btn-outline-danger btn-sm">
                                           <input hidden name="id" value="${incidencias.id_incidencia}">
                                            <i data-feather="edit"></i> RECHAZAR
                                        </button>
                                </form>

                            </div>
                           </div>
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
</section>

<jsp:include page="/layouts/footer.jsp"/>

    <script>
        window.addEventListener("DOMContentLoaded", () => {
            if (!${param['result']==false?param['result']:true}) {
                Swal.fire({
                    title: 'Información...',
                    text: '${param['message']}',
                    icon: '${param['result']==false ? 'success':'error'}',
                    confirmButtonText: 'Aceptar'
                });
            }
            if (!${param['result']==false?param['result']:true}) {
                Swal.fire({
                    title: 'Información...',
                    text: '${param['message']}',
                    icon: 'success',
                    confirmButtonText: 'Aceptar'
                });
            }
            if (${param['result']==true?param['result']:false}) {
                Swal.fire({
                    title: 'Información...',
                    text: '${param['message']}',
                    icon: 'succes',
                    confirmButtonText: 'Aceptar'
                });
            }
        }, false);
    </script>
</body>
</html>
