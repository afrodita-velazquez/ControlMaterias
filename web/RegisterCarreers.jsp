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
                <h1 class="m-0">Registro de Carreras</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
                <ol class="breadcrumb float-sm-right">
                    <li class="breadcrumb-item"><a href="Dashboard">Inicio</a></li>
                    <li class="breadcrumb-item active">Registro de Carreras</li>
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
                <form method="POST" id="formCarreers">
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="exampleFormControlFile1">Clave de Carrera</label>
                                <input class="form-control form-control-sm" type="text" placeholder="Clave de Carrera" name="carreras_clave" required="">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="exampleFormControlFile1">Nombre de la Carrera</label>
                                <input class="form-control form-control-sm" type="text" placeholder="Nombre" name="carreras_nombre" required="">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="exampleFormControlFile1">Departamento</label>
                                <select class="form-control form-control-sm" id="dep_select" name="departamento_id" required="">
                                    <option value="">Seleccione...</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" value="insertCarreer" name="action"/>
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
                            <th>Clave Carrera</th>
                            <th>Nombre Carrera</th>
                            <th>Departamento</th>
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
<%@ include file="RegisterCarreers_js.jsp" %>
<script>
    $(document).ready(function () {
        $($($(".menuCarReg")[0]).parent()[0]).addClass("active");
    });
</script>