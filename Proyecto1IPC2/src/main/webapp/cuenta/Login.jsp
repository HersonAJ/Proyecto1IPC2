<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Revista Digital</title>
    <jsp:include page="/resources/resources.jsp" />

    <style>
        .form-signin {
            max-width: 330px;
            padding: 15px;
            margin: auto;
        }
        .form-signin .form-floating:focus-within {
            z-index: 2;
        }
        .form-signin input[type="email"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }
        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
    </style>
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">
    <main class="form-signin w-100 m-auto">
        <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
            <img class="mb-4" src="../resources/bootstrap-icons/box-arrow-in-right.svg" alt="Login Icon" width="72" height="57">
            <h1 class="h3 mb-3 fw-normal">Por favor, inicia sesión</h1>

            <div class="form-floating">
                <input type="email" class="form-control" id="username" name="username" placeholder="name@example.com" required>
                <label for="floatingInput">Correo electrónico</label>
            </div>
            <div class="form-floating">
                <input type="password" class="form-control" id="floatingPassword" name="password" placeholder="Contraseña" required>
                <label for="floatingPassword">Contraseña</label>
            </div>

            <div class="form-check text-start my-3">
                <input class="form-check-input" type="checkbox" value="remember-me" id="flexCheckDefault">
                <label class="form-check-label" for="flexCheckDefault">
                    Recuérdame
                </label>
            </div>
            <button class="btn btn-primary w-100 py-2" type="submit">Iniciar sesión</button>
                    <p>¿No tienes una cuenta? <a href="Registro.jsp">Regístrate aquí</a></p>
            
            <p class="mt-5 mb-3 text-body-secondary">© 2017–2024</p>
            
            
        </form>
    </main>
</body>
</html>
