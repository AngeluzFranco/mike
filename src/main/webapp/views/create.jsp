<%--
  Created by IntelliJ IDEA.
  User: angry
  Date: 22/08/2023
  Time: 11:49 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registro de falta o retardo</title>
    <jsp:include page="/layouts/head.jsp"/>
</head>
<body>
<section class="vh-100" style="background-color: #508bfc;">


<div class="container-fluid">
    <div class="row">
        <div class="col">
            <div class="card mt-5">
                <div class="card-header">Crear incidencia</div>
                <div class="card-body">
                    <form id="incidencia-form" class="needs-validation" novalidate action="/incidencia/save" method="post">
                        <div class="form-group mb-3">
                            <div class="row">
                                <div class="col">
                                    <label for="titulo" class="fw-bold">Titulo: </label>
                                    <input type="text" name="titulo" id="titulo" class="form-control"
                                           required/>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>
                                <div class="col">
                                    <label for="descripcion" class="fw-bold">Descripcion: </label>
                                    <input type="text" name="descripcion" id="descripcion" class="form-control"
                                           required/>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>
                                <div class="col">
                                    <label for="tipo" class="fw-bold">Tipo: </label>
                                    <input type="text" name="tipo" id="tipo" class="form-control"
                                           required/>
                                    <div class="invalid-feedback">Campo obligatorio</div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group mb-3">
                            <div class="row">
                                <div class="col text-end">
                                    <a href="/incidencia/incidencias" class="btn btn-outline-danger btn-sm">
                                        CANCELAR
                                    </a>
                                    <button type="submit" class="btn btn-outline-success btn-sm">
                                        ACEPTAR
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</section>
<jsp:include page="../layouts/footer.jsp"/>
<script>
    (function () {
        const form = document.getElementById("incidencia-form");
        form.addEventListener("submit", function (event) {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add("was-validated");
        }, false);
    })();
</script>

</body>
</html>
