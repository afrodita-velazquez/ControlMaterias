<script>
    $(document).ready(function () {
        fillTable();
        $("#formSchedule").submit(function (event) {
            insertSchedule($(this));
            event.preventDefault();
        });
    });

    function fillDepartments() {
        $.ajax({
            url: 'RegisterCarreers?action=getDepartments',
            type: 'get',
            dataType: 'json',
            success: function (result) {
                console.log(result);
                var html = "";
                $.each(result, function (i, val) {
                    if ('<%= request.getSession(false).getAttribute("grupo_usuario_id").toString()%>' === '2') {
                        if (val.departamento_id == <%=request.getSession(false).getAttribute("grupo_usuario_id").toString()%>) {
                            html += '<option value="' + val.departamento_id + '">' + val.departamento_nombre + '</option>';
                        }
                    } else {
                        html += '<option value="' + val.departamento_id + '">' + val.departamento_nombre + '</option>';
                    }
                });
                $("#departamento_select").append(html);
                if ('<%= request.getSession(false).getAttribute("grupo_usuario_id").toString()%>' === '2') {
                    $("#departamento_select").val(<%= request.getSession(false).getAttribute("departamento_id").toString()%>);
                    $("#departamento_select").prop('readOnly', true);
                }
            }
        });
    }

    function insertSchedule(form) {
        var post_args = form.serializeArray();
        $.ajax({
            url: 'RegisterClasses',
            type: 'post',
            data: post_args,
            dataType: 'json',
            success: function (result) {
                console.log(result[0].ok);
                if (result[0].ok) {
                    Swal.fire({
                        title: '¡Listo!',
                        text: "La clase se ha asignó correctamente",
                        icon: 'success'
                    }).then((result) => {
                        fillProfesorTable($("#usuario_id_modal").val());
                    });
                } else {
                    Swal.fire({
                        title: '¡Error!',
                        text: "La clase no se pudo asignar",
                        icon: 'error'
                    });
                }
            }
        });
    }

    function fillTable() {
        $('#subjectsTable').DataTable({
            destroy: true,
            ajax: {
                url: "Signin?action=getInsertedUsers",
                type: "GET",
                dataSrc: ''
            },
            columns: [
                {data: 'usuario_id'},
                {data: 'persona_nombre'},
                {data: 'persona_appat'},
                {data: 'persona_apmat'},
                {data: 'persona_tel'},
                {data: 'persona_email'},
                {data: 'grupo_usuario_descripcion'},
                {data: 'departamento_nombre'},
                {data: 'acciones'}
            ]
        });
    }
    
    function fillProfesorTable(usuario_id) {
        $('#porfessorTable').DataTable({
            destroy: true,
            ajax: {
                url: "RegisterClasses?action=getInsertedScheduleByUserId&usuario_id="+usuario_id,
                type: "GET",
                dataSrc: ''
            },
            columns: [
                {data: 'clase_id'},
                {data: 'materia_nombre'},
                {data: 'dias_grupo_grupo'},
                {data: 'hora_clase_inicio'},
                {data: 'hora_clase_fin'},
                {data: 'acciones'}
            ]
        });
    }


    function openModal(usu_id) {
        fillDepartments();
        fillProfesorTable(usu_id);
        $("#modalAsignacionMaterias").modal();
        $("#usuario_id_modal").val(usu_id);
    }

    function fillDepartments() {
        $.ajax({
            url: 'RegisterClasses?action=getInsertedClasses',
            type: 'get',
            dataType: 'json',
            success: function (result) {
                console.log(result);
                var html = '<option value="">Seleccione...</option>';
                $.each(result, function (i, val) {
                    html += '<option value="' + val.clase_id + '">' + val.materia_nombre + ' ' + val.dias_grupo_grupo + ' (' + val.hora_clase_inicio + ' - ' + val.hora_clase_fin + ')</option>';
                });
                console.log(html);
                $("#clase_select").html(html);
            }
        });
    }
</script>