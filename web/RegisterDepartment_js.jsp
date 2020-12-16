<script>
    $(document).ready(function () {
        fillTable();
        fillCampus();
        $("#formDepartment").submit(function (event) {
            insertDepartment($(this));
            event.preventDefault();
        });
    });

    function fillCampus() {
        $.ajax({
            url: 'RegisterDepartment?action=getCampus',
            type: 'get',
            dataType: 'json',
            success: function (result) {
                var html = "";
                $.each(result, function (i, val) {
                    html += '<option value="' + val.campus_id + '">' + val.campus_nombre + '</option>';
                });
                $("#dep_campus").append(html);
            }
        });
    }

    function insertDepartment(form) {
        var post_args = form.serializeArray();
        $.ajax({
            url: 'RegisterDepartment',
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

    function fillTable() {
        $('#subjectsTable').DataTable({
            destroy: true,
            ajax: {
                url: "RegisterDepartment?action=getInsertedDepartments",
                type: "GET",
                dataSrc: ''
            },
            columns: [
                {data: 'departamento_id'},
                {data: 'departamento_nombre'},
                {data: 'campus_nombre'}
            ]
        });
    }
</script>