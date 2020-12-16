<script>
    $(document).ready(function () {
        fillTable();
        fillDepartments();
        $("#formCarreers").submit(function (event) {
            insertCarreers($(this));
            event.preventDefault();
        });
    });

    function fillDepartments() {
        $.ajax({
            url: 'RegisterCarreers?action=getDepartments',
            type: 'get',
            dataType: 'json',
            success: function (result) {
                var html = "";
                $.each(result, function (i, val) {
                    html += '<option value="' + val.departamento_id + '">' + val.departamento_nombre + '</option>';
                });
                $("#dep_select").append(html);
            }
        });
    }
    
    function fillTable() {
        $('#subjectsTable').DataTable({
            destroy: true,
            ajax: {
                url: "RegisterCarreers?action=getInsertedCarreers",
                type: "GET",
                dataSrc: ''
            },
            columns: [
                {data: 'carreras_id'},
                {data: 'carreras_clave'},
                {data: 'carreras_nombre'},
                {data: 'departamento_nombre'}
            ]
        });
    }
    
    function insertCarreers(form) {
        var post_args = form.serializeArray();
        $.ajax({
            url: 'RegisterCarreers',
            type: 'post',
            data: post_args,
            dataType: 'json',
            success: function (result) {
                console.log(result[0].ok);
                if (result[0].ok) {
                    Swal.fire({
                        title: '¡Listo!',
                        text: "El departamento se ha registrado correctamente",
                        icon: 'success'
                    }).then((result) => {
                        fillTable();
                    });
                } else {
                    Swal.fire({
                        title: '¡Error!',
                        text: "El departamento no se pudo registrar",
                        icon: 'error'
                    });
                }
            }
        });
    }
</script>