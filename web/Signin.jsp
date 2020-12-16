<%@page import="java.util.ArrayList"%>
<%@page import="models.classes.Menu"%>
<%@ include file="core/header.jsp" %>

<%@ include file="core/navbar_header.jsp" %>
<!-- Contenido NavBar -->



<!-- /Contenido NavBar -->
<%@ include file="core/navbar_footer.jsp" %>
<%@ include file="core/sidebar_header.jsp" %>
<!-- Contenido SideBar -->
<%
    HttpSession misession = request.getSession(true);
    ArrayList<Menu> Menus = (ArrayList<Menu>) misession.getAttribute("usuario_menus");
    for (Menu menu : Menus) {
%>
<li class="nav-item">
    <a href="<%=menu.getMenu_url()%>" class="nav-link">
        <i class="<%=menu.getMenu_icono()%>"></i>
        <p><%=menu.getMenu_nombre()%></p>
    </a>
</li>
<%
    }
%>

<!-- /Contenido SideBar -->
<%@ include file="core/sidebar_foother.jsp" %>
<%@ include file="core/content_header.jsp" %>
<!-- Contenido -->

<!-- Content Header (Page header) -->
<div class="content-header">
    <div class="container-fluid">
        <div class="row mb-2">
            <div class="col-sm-6">
                <h1 class="m-0">Registro de Usuarios</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
                <ol class="breadcrumb float-sm-right">
                    <li class="breadcrumb-item"><a href="#">Inicio</a></li>
                    <li class="breadcrumb-item active">Registro de Usuarios</li>
                </ol>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.container-fluid -->
</div>
<!-- /.content-header -->

<div class="row ml-3 mr-3">
    <div class="col-sm">
        <div class="card mt-2">
            <div class="card-body">
                <form method="POST" id="userForm">
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="exampleFormControlFile1">Nombre</label>
                                <input class="form-control form-control-sm" type="text" placeholder="Nombre" name="persona_nombre">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="exampleFormControlFile1">Apellido Paterno</label>
                                <input class="form-control form-control-sm" type="text" placeholder="Apellido Paterno" name="persona_appat">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="exampleFormControlFile1">Apellido Materno</label>
                                <input class="form-control form-control-sm" type="text" placeholder="Apellido Materno" name="persona_apmat">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="exampleFormControlFile1">Teléfono</label>
                                <input class="form-control form-control-sm" type="text" placeholder="Teléfono" name="persona_tel">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="exampleFormControlFile1">Correo Electrónico</label>
                                <input class="form-control form-control-sm" type="email" placeholder="Correo Electrónico" name="persona_email">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="exampleFormControlFile1">Tipo Usuario</label>
                                <select class="form-control form-control-sm" name="grupo_usuario_id">
                                    <option value="">Seleccione...</option>
                                    <%
                                        int grupo_usuario_id = Integer.parseInt(request.getSession(false).getAttribute("grupo_usuario_id").toString());
                                        if (grupo_usuario_id == 1) {
                                    %>
                                    <option value="2">Jefe de Carrera</option>
                                    <%                                        
                                        }
                                    %>
                                    <option value="3">Profesor</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="exampleFormControlFile1">Departamento</label>
                                <select class="form-control form-control-sm" id="departamento_select" name="departamento_id">
                                    <option value="">Seleccione...</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="exampleFormControlFile1">Usuario</label>
                                <input class="form-control form-control-sm" type="text" placeholder="Usuario" name="usuario_login">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="exampleFormControlFile1">Contraseña</label>
                                <input class="form-control form-control-sm" type="password" placeholder="Contraseña" name="usuario_pwd" id="pwd">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="exampleFormControlFile1">Repetir Contraseña</label>
                                <input class="form-control form-control-sm" type="password" placeholder="Repetir Contraseña" id="pwdrepeat">
                            </div>
                        </div>
                    </div>
                    <input type="hidden" value="insertUser" name="action"/>
                    <button type="submit" class="btn btn-secondary btn-sm">Agregar</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="row ml-3 mr-3">
    <div class="col-sm">
        <div class="card mt-2">
            <div class="card-body">
                <table id="subjectsTable">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Nombre</th>
                            <th>Apellido Paterno</th>
                            <th>Apellido Materno</th>
                            <th>Teléfono</th>
                            <th>Correo Electrónico</th>
                            <th>Tipo de Usuario</th>
                            <th>Departamento</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>


<!-- /Contenido -->
<%@ include file="core/content_footer.jsp" %>


<%@ include file="core/footer.jsp" %>
<%@ include file="Signin_js.jsp" %>
<script>
    $(document).ready(function () {
        $($($(".menuUsuReg")[0]).parent()[0]).addClass("active");
    });
</script>