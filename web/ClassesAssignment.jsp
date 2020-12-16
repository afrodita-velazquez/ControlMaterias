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

<div class="content-header">
    <div class="container-fluid">
        <div class="row mb-2">
            <div class="col-sm-6">
                <h1 class="m-0">Asignación de Carreras</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
                <ol class="breadcrumb float-sm-right">
                    <li class="breadcrumb-item"><a href="Dashboard">Inicio</a></li>
                    <li class="breadcrumb-item active">Asignación de Carreras</li>
                </ol>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.container-fluid -->
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

<div class="modal" id="modalAsignacionMaterias" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-xl" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Asignar Materias a Profesor</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row ml-3 mr-3">
                    <div class="col-sm">
                        <div class="card mt-2">
                            <div class="card-body">
                                <form method="POST" id="formSchedule">
                                    <div class="row">
                                        <div class="col-sm-10">
                                            <div class="form-group">
                                                <label for="exampleFormControlFile1">Clases</label>
                                                <select class="form-control form-control-sm" id="clase_select" name="clase_id">

                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <input type="hidden" value="" name="usuario_id" id="usuario_id_modal"/>
                                    <input type="hidden" value="insertSchedule" name="action"/>
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
                                <table id="porfessorTable">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Materia</th>
                                            <th>Días</th>
                                            <th>Hora Inicio</th>
                                            <th>Hora Fin</th>
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
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!-- /Contenido -->
<%@ include file="core/content_footer.jsp" %>


<%@ include file="core/footer.jsp" %>
<%@ include file="ClassesAssignment_js.jsp" %>
<script>
    $(document).ready(function () {
        $($($(".menuClasAss")[0]).parent()[0]).addClass("active");
    });
</script>