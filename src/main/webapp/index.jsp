<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
  <jsp:include page="./layouts/head.jsp"/>
</head>
<body style="background-color: #6e7881">

<section class="vh-100">
  <div class="container-fluid h-custom">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-md-9 col-lg-6 col-xl-5">
        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
             class="img-fluid" alt="Sample image">
      </div>
      <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
        <form id="loginForm" action="/api/auth" class="needs-validation" novalidate method="post">

          <!-- Email input -->
          <div class="form-outline mb-4">
            <input type="text" class="form-control" name="username" id="username" placeholder="name" required/>
            <label for="username">Username</label>
            <div class="invalid-feedback text-start">
              Campo obligatorio
            </div>
          </div>

          <!-- Password input -->
          <div class="form-outline mb-3">
            <input type="password" name="password" class="form-control" id="password" placeholder="Password" required>
            <label for="password">Contraseña</label>
            <div class="invalid-feedback text-start">
              Campo obligatorio
            </div>
          </div>

          <div class="d-flex justify-content-between align-items-center">
            <!-- Checkbox -->
            <div class="form-check mb-0">
              <input class="form-check-input me-2" type="checkbox" value="" id="form2Example3" />
              <label class="form-check-label" for="form2Example3">
                Remember me
              </label>
            </div>
            <a href="#!" class="text-body">Forgot password?</a>
          </div>

          <div class="text-center text-lg-start mt-4 pt-2">
            <button id="login" class="btn btn-success w-80" type="submit">
              <i data-feather="log-in"></i> INICIAR SESIÓN
            </button>
            <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? <a href="#!"
                                                                              class="link-danger">Register</a></p>
          </div>

        </form>
      </div>
    </div>
  </div>


<jsp:include page="./layouts/footer.jsp"/>
<!-- -->
<script>
  window.addEventListener("DOMContentLoaded", () => {
    feather.replace();
    const form = document.getElementById("loginForm");
    const btn = document.getElementById("login");
    form.addEventListener('submit', event => {
      btn.innerHTML = `<div class="d-flex justify-content-center">
                                <div class="spinner-border" role="status">
                                    <span class="visually-hidden">Loading...</span>
                                </div>
                            </div>`;
      btn.classList.add("disabled");
      if (!form.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
        btn.classList.remove("disabled");
        btn.innerHTML = `<i data-feather="log-in"></i> INICIAR SESIÓN`;
      }
      form.classList.add('was-validated');
    }, false);
    if (!${param['result']}) {
      Swal.fire({
        title: 'Acceso denegado',
        text: '${param['message']}',
        icon: 'error',
        confirmButtonText: 'Aceptar'
      });
    }
  }, false);
</script>
</body>
</html>