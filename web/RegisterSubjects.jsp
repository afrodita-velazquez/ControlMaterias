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
                <h1 class="m-0">Registro de Materias</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
                <ol class="breadcrumb float-sm-right">
                    <li class="breadcrumb-item"><a href="#">Inicio</a></li>
                    <li class="breadcrumb-item active">Registro de Materias</li>
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
                <form method="POST" id="subjectForm">
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="exampleFormControlFile1">Clave de la Materia</label>
                                <input class="form-control form-control-sm" type="text" placeholder="Clave de la Materia" name="materia_clave" required="">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="exampleFormControlFile1">Nombre de la Materia</label>
                                <input class="form-control form-control-sm" type="text" placeholder="Nombre de la Materia" name="materia_nombre" required="">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="exampleFormControlFile1">Carrera</label>
                                <select class="form-control form-control-sm" id="carrera_select" name="carreras_id" required="">
                                    <option value="">Seleccione...</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="exampleFormControlFile1">N° Créditos</label>
                                <input class="form-control form-control-sm" type="text" placeholder="Créditos" id="materia_creditos" name="materia_creditos" required="">
                            </div>
                        </div>
                    </div>
                    <input type="hidden" value="insertSubject" name="action"/>
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
                            <th>Clave</th>
                            <th>Nombre</th>
                            <th>Carrera</th>
                            <th>Créditos</th>
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

<div class="row ml-3 mr-3">
    <div class="col-sm">
        <div class="card mt-2">
            <div class="card-body">
                <form id="formDownloadSubjectsReport">
                    <h3 class="m-0">Reporte de Materias por Carrera</h3>
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="exampleFormControlFile1">Carrera</label>
                                <select class="form-control form-control-sm" id="carreras_id_reporte" name="carreras_id_reporte" required="">
                                    <option value="">Seleccione...</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <button type="submit" id="downloadSchedule" class="btn btn-primary ml-2">Descargar Reporte Materias</button>
                    </div>
                </form>
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
                                <form method="POST" id="formModifySubject">
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <div class="form-group">
                                                <label for="exampleFormControlFile1">Clave de la Materia</label>
                                                <input class="form-control form-control-sm" type="text" id="materia_clave_modal" placeholder="Clave de la Materia" name="materia_clave" required="">
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="form-group">
                                                <label for="exampleFormControlFile1">Nombre de la Materia</label>
                                                <input class="form-control form-control-sm" type="text" id="materia_nombre_modal" placeholder="Nombre de la Materia" name="materia_nombre" required="">
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="form-group">
                                                <label for="exampleFormControlFile1">Carrera</label>
                                                <select class="form-control form-control-sm" id="carrera_select_modal" name="carreras_id" required="">
                                                    <option value="">Seleccione...</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <div class="form-group">
                                                <label for="exampleFormControlFile1">N° Créditos</label>
                                                <input class="form-control form-control-sm" type="text" placeholder="Créditos" id="materia_creditos_modal" name="materia_creditos" required="">
                                            </div>
                                        </div>
                                    </div>
                                    <input type="hidden" value="" name="materia_id" id="materia_id_modal"/>
                                    <input type="hidden" value="updateSubject" name="action"/>
                                    <button type="submit" class="btn btn-secondary btn-sm">Agregar</button>
                                </form>
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
<%@ include file="RegisterSubjects_js.jsp" %>
<script>
    $(document).ready(function () {
        $($($(".menuMatReg")[0]).parent()[0]).addClass("active");
    });
</script>