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
                <h1 class="m-0">Horario</h1>
            </div><!-- /.col -->
            <div class="col-sm-6">
                <ol class="breadcrumb float-sm-right">
                    <li class="breadcrumb-item"><a href="Dashboard">Inicio</a></li>
                    <li class="breadcrumb-item active">Horario</li>
                </ol>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.container-fluid -->
</div>

<div class="row ml-3 mr-3">
    <div class="col-sm">
        <div class="card mt-2">
            <div class="card-body">
                <button type="button" id="downloadSchedule" class="btn btn-primary">Descargar Horario</button>
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
<%@ include file="TeacherSchedule_js.jsp" %>
<script>
    $(document).ready(function () {
        $($($(".menuTeaSch")[0]).parent()[0]).addClass("active");
    });
</script>