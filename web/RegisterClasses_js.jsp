<script>
    $(document).ready(function () {
        fillTable();
        fillMaterias();
        fillDias();
        fillHoras();
        $("#formClasses").submit(function (event) {
            insertClasses($(this));
            event.preventDefault();
        });
    });

    function fillMaterias() {
        $.ajax({
            url: 'RegisterClasses?action=getMaterias',
            type: 'get',
            dataType: 'json',
            success: function (result) {
                var html = "";
                $.each(result, function (i, val) {
                    html += '<option value="' + val.materia_id + '">' + val.materia_clave + ' '  + val.materia_nombre +  '</option>';
                });
                $("#materia_select").append(html);
            }
        });
    }
    
    function fillDias() {
        $.ajax({
            url: 'RegisterClasses?action=getDias',
            type: 'get',
            dataType: 'json',
            success: function (result) {
                var html = "";
                $.each(result, function (i, val) {
                    html += '<option value="' + val.dias_grupo_id + '">' + val.dias_grupo_grupo + '</option>';
                });
                $("#dias_select").append(html);
            }
        });
    }
    
    function fillHoras() {
        $.ajax({
            url: 'RegisterClasses?action=getHoras',
            type: 'get',
            dataType: 'json',
            success: function (result) {
                var html = "";
                $.each(result, function (i, val) {
                    html += '<option value="' + val.hora_clase_id + '">' + val.hora_clase_inicio + ' - ' + val.hora_clase_fin + '</option>';
                });
                $("#hora_select").append(html);
            }
        });
    }

    function insertClasses(form) {
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
                        text: "La clase se ha registrado correctamente",
                        icon: 'success'
                    }).then((result) => {
                        fillTable();
                    });
                } else {
                    Swal.fire({
                        title: '¡Error!',
                        text: "La clase no se pudo registrar",
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
                url: "RegisterClasses?action=getInsertedClasses",
                type: "GET",
                dataSrc: ''
            },
            columns: [
                {data: 'clase_id'},
                {data: 'materia_nombre'},
                {data: 'dias_grupo_grupo'},
                {data: 'hora_clase_inicio'},
                {data: 'hora_clase_fin'}
            ]
        });
    }
</script>