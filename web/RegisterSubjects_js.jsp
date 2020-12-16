<script>
    $(document).ready(function () {
        fillCarreers();
        fillTable();
        $("#subjectForm").submit(function (event) {
            if (Number.isInteger(parseInt($("#materia_creditos").val()))) {
                insertCarreers($(this));
            } else {
                Swal.fire({
                    title: 'Error',
                    text: 'El campo "N° Créditos" debe ser un valor numérico',
                    icon: 'error'
                }).then((result) => {
                    $("#materia_creditos").focus();
                });
            }
            event.preventDefault();
        });
        
        $("#formModifySubject").submit(function (event) {
            if (Number.isInteger(parseInt($("#materia_creditos_modal").val()))) {
                updateSubject($(this));
            } else {
                Swal.fire({
                    title: 'Error',
                    text: 'El campo "N° Créditos" debe ser un valor numérico',
                    icon: 'error'
                }).then((result) => {
                    $("#materia_creditos").focus();
                });
            }
            event.preventDefault();
        });
        
        $("#formDownloadSubjectsReport").submit(function (event) {
            console.log($(this).serializeArray()[0].value);
            var carrer_id = $(this).serializeArray()[0].value;
            downloadSubjectReport(carrer_id);
            event.preventDefault();
        });
    });

    function fillTable() {
        $('#subjectsTable').DataTable({
            destroy: true,
            ajax: {
                url: "RegisterSubjects?action=getInsertedSubjects",
                type: "GET",
                dataSrc: ''
            },
            columns: [
                {data: 'materia_id'},
                {data: 'materia_clave'},
                {data: 'materia_nombre'},
                {data: 'carrera_nombre'},
                {data: 'materia_creditos'},
                {data: 'acciones'}
            ]
        });
    }

    function fillCarreers() {
        $.ajax({
            url: 'RegisterSubjects?action=getCarreers',
            type: 'get',
            dataType: 'json',
            success: function (result) {
                var html = "";
                $.each(result, function (i, val) {
                    html += '<option value="' + val.carreras_id + '">' + val.carreras_nombre + '</option>';
                });
                $("#carrera_select").append(html);
                $("#carreras_id_reporte").append(html);
                $("#carrera_select_modal").append(html);
            }
        });
    }

    function insertCarreers(form) {
        var post_args = form.serializeArray();
        console.log(post_args);
        $.ajax({
            url: 'RegisterSubjects',
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
                        form.trigger("reset");
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
    
    function updateSubject(form) {
        var post_args = form.serializeArray();
        console.log(post_args);
        $.ajax({
            url: 'RegisterSubjects',
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
                        form.trigger("reset");
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

    function openModal(usu_id) {
        fillSubjectById(usu_id);
        $("#modalAsignacionMaterias").modal();
        $("#usuario_id_modal").val(usu_id);
    }

    function fillSubjectById(usu_id) {
        $.ajax({
            url: 'RegisterSubjects?action=getSubjectById&materia_id=' + usu_id,
            type: 'get',
            dataType: 'json',
            success: function (result) {
                console.log(result);
                $("#materia_clave_modal").val(result[0].materia_clave);
                $("#materia_nombre_modal").val(result[0].materia_nombre);
                //$("#carrera_select_modal").val(result[0].materia_clave);
                $("#materia_creditos_modal").val(result[0].materia_creditos);
                $("#materia_id_modal").val(result[0].materia_id);
            }
        });
    }
    
    function downloadSubjectReport(carrer_id) {
        $.ajax({
            url: 'RegisterSubjects?action=getInsertedSubjectsByCarreerId&carrera_id=' + carrer_id,
            type: 'get',
            dataType: 'json',
            success: function (result) {
                console.log(result);
                var resultado = result;
                $.each(resultado, function (key, value) {
                    delete(value['acciones']);
                    delete(value['materia_id']);
                });
                JSONToCSVConvertor(resultado, resultado[0].carrera_nombre, true);
            }
        });
    }
</script>