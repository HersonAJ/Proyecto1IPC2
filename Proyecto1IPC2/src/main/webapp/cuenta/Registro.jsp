<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro de Usuario</title>
    <jsp:include page="/resources/resources.jsp" />
    <script>
        function mostrarSaldoCartera() {
            var tipoUsuario = document.getElementById("tipoUsuario").value;
            var saldoCarteraDiv = document.getElementById("saldoCarteraDiv");
            if (tipoUsuario === "Comprador_Anuncios") {
                saldoCarteraDiv.style.display = "block";
            } else {
                saldoCarteraDiv.style.display = "none";
            }
        }
    </script>
</head>
<body>
    <jsp:include page="/resources/header.jsp" />
    <main class="container my-4">
        <h2>Registro de Usuario</h2>
        <form action="RegistrarUsuarioServlet" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre:</label>
                <input type="text" class="form-control" id="nombre" name="nombre" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Correo Electrónico:</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="mb-3">
                <label for="contraseña" class="form-label">Contraseña:</label>
                <input type="password" class="form-control" id="contraseña" name="contraseña" required>
            </div>
            <div class="mb-3">
                <label for="fotoPerfil" class="form-label">Foto de Perfil:</label>
                <input type="file" class="form-control" id="fotoPerfil" name="fotoPerfil" accept=".jpg, .png">
            </div>
            <div class="mb-3">
                <label for="descripcion" class="form-label">Descripción:</label>
                <textarea class="form-control" id="descripcion" name="descripcion"></textarea>
            </div>
            <div class="mb-3">
                <label for="hobbies" class="form-label">Hobbies:</label>
                <select class="form-select" id="hobbies" name="hobbies" multiple>
                    <option value="lectura">Lectura</option>
                    <option value="deportes">Deportes</option>
                    <option value="musica">Música</option>
                    <option value="viajes">Viajes</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="temasInteres" class="form-label">Temas de Interés:</label>
                <select class="form-select" id="temasInteres" name="temasInteres" multiple>
                    <option value="tecnologia">Tecnología</option>
                    <option value="ciencia">Ciencia</option>
                    <option value="arte">Arte</option>
                    <option value="historia">Historia</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="tipoUsuario" class="form-label">Tipo de Usuario:</label>
                <select class="form-select" id="tipoUsuario" name="tipoUsuario" onchange="mostrarSaldoCartera()">
                    <option value="editor">Editor</option>
                    <option value="suscriptor">Suscriptor</option>
                    <option value="administrador">Administrador</option>
                    <option value="Comprador_Anuncios">Comprador de Anuncios</option>
                </select>
            </div>
            <div id="saldoCarteraDiv" class="mb-3" style="display: none;">
                <label for="saldoCartera" class="form-label">Saldo en Cartera:</label>
                <input type="number" class="form-control" id="saldoCartera" name="saldoCartera" step="0.01">
            </div>
            <button type="submit" class="btn btn-primary">Registrar</button>
        </form>
    </main>
    <footer class="text-center py-4">
        <div class="footer-links">
            <a href="#">Contacto</a>
            <a href="#">Privacidad</a>
            <a href="#">Términos</a>
        </div>
    </footer>
</body>
</html>
