<script>
    $(document).ready(function () {
        fillDepartments();
        fillTable();
        $("#userForm").submit(function (event) {
            if ($("#pwd").val() == $("#pwdrepeat").val()) {
                insertUser($(this));
            } else {
                Swal.fire({
                    title: '¡Error!',
                    text: "Las contraseñas no coinciden",
                    icon: 'error'
                });
            }
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
                        if(val.departamento_id == <%=request.getSession(false).getAttribute("grupo_usuario_id").toString()%>){
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

    function insertUser(form) {
        var post_args = form.serializeArray();
        $.ajax({
            url: 'Signin',
            type: 'post',
            data: post_args,
            dataType: 'json',
            success: function (result) {
                console.log(result[0].ok);
                if (result[0].ok) {
                    Swal.fire({
                        title: '¡Listo!',
                        text: "El usuario se ha registrado correctamente",
                        icon: 'success'
                    }).then((result) => {
                        fillTable();
                    });
                } else {
                    Swal.fire({
                        title: '¡Error!',
                        text: "El usuario no se pudo registrar",
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
</script>