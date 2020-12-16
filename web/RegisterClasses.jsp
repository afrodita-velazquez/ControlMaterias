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
                <h1 class="m-0">Registro de Clases</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
                <ol class="breadcrumb float-sm-right">
                    <li class="breadcrumb-item"><a href="#">Inicio</a></li>
                    <li class="breadcrumb-item active">Registro de Clases</li>
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
                <form method="POST" id="formClasses">
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="exampleFormControlFile1">Materia</label>
                                <select class="form-control form-control-sm" id="materia_select" name="materia_id">
                                    <option value="">Seleccione...</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="exampleFormControlFile1">Días</label>
                                <select class="form-control form-control-sm" id="dias_select" name="dias_grupo_id">
                                    <option value="">Seleccione...</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="exampleFormControlFile1">Hora</label>
                                <select class="form-control form-control-sm" id="hora_select" name="hora_clase_id">
                                    <option value="">Seleccione...</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" value="insertClasses" name="action"/>
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
                            <th>Materia</th>
                            <th>Días</th>
                            <th>Hora Inicio</th>
                            <th>Hora Fin</th>
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
<%@ include file="RegisterClasses_js.jsp" %>
<script>
    $(document).ready(function () {
        $($($(".menuClasReg")[0]).parent()[0]).addClass("active");
    });
</script>