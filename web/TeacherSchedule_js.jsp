<script>
    $(document).ready(function () {
        fillProfesorTable();
        $("#downloadSchedule").click(function () {
            downloadSchedule();
        });
    });

    function fillProfesorTable() {
        $('#porfessorTable').DataTable({
            destroy: true,
            dom: 'Bfrtip',
            buttons: [
                'csv', 'excel', 'pdf'
            ],
            ajax: {
                url: "RegisterClasses?action=getInsertedProfesorSchedule",
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

    function downloadSchedule() {
        $.ajax({
            url: 'RegisterClasses?action=getInsertedProfesorSchedule',
            type: 'get',
            dataType: 'json',
            success: function (result) {
                var resultado = result;
                $.each(resultado, function (key, value) {
                    delete(value['acciones']);
                    delete(value['clase_id']);
                });
                JSONToCSVConvertor(resultado, "Horario Profesor", true);
            }
        });
    }
</script>