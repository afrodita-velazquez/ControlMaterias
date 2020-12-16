           
<footer class="main-footer">
    <strong>Copyright &copy; 2014-2020 <a href="https://adminlte.io">AdminLTE.io</a>.</strong>
    All rights reserved.
    <div class="float-right d-none d-sm-inline-block">
        <b>Version</b> 3.1.0-rc
    </div>
</footer>

<!-- Control Sidebar -->
<aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
</aside>
<!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<!-- jQuery -->
<script src="plugins/jquery/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
    $.widget.bridge('uibutton', $.ui.button)
</script>
<!-- Bootstrap 4 -->
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- ChartJS -->
<script src="plugins/chart.js/Chart.min.js"></script>
<!-- Sparkline -->
<script src="plugins/sparklines/sparkline.js"></script>
<!-- JQVMap -->
<script src="plugins/jqvmap/jquery.vmap.min.js"></script>
<script src="plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
<!-- jQuery Knob Chart -->
<script src="plugins/jquery-knob/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="plugins/moment/moment.min.js"></script>
<script src="plugins/daterangepicker/daterangepicker.js"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
<!-- Summernote -->
<script src="plugins/summernote/summernote-bs4.min.js"></script>
<!-- overlayScrollbars -->
<script src="plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="dist/js/pages/dashboard.js"></script>
<!-- datatable -->
<script src="core/dist/js/jquery.dataTables.js"></script>
<script src="core/dist/js/dataTables.bootstrap4.min.js"></script>
<script src="core/dist/js/dataTables.buttons.min.js"></script>
<script src="core/dist/js/dataTables.responsive.min.js"></script>
<script src="core/dist/js/responsive.bootstrap4.min.js"></script>
<!-- sweetAlerts -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<!-- SheetJs -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.16.8/xlsx.min.js" integrity="sha512-8y82APPyIOwaBCEuQoZM4KdySliHcT2dQwZzkdOx7l2LfGS01ZvNg0A7PpplNTYN66tz7skkFr4rihZQhf9yGA==" crossorigin="anonymous"></script>
<script>
    function JSONToCSVConvertor(JSONData, ReportTitle, ShowLabel) {

//If JSONData is not an object then JSON.parse will parse the JSON string in an Object
        var arrData = typeof JSONData != 'object' ? JSON.parse(JSONData) : JSONData;
        var CSV = '';
//This condition will generate the Label/Header
        if (ShowLabel) {
            var row = "";

//This loop will extract the label from 1st index of on array
            for (var index in arrData[0]) {
//Now convert each value to string and comma-seprated
                row += index + ',';
            }
            row = row.slice(0, -1);
//append Label row with line break
            CSV += row + '\r\n';
        }

//1st loop is to extract each row
        for (var i = 0; i < arrData.length; i++) {
            var row = "";
//2nd loop will extract each column and convert it in string comma-seprated
            for (var index in arrData[i]) {
                row += '"' + arrData[i][index] + '",';
            }
            row.slice(0, row.length - 1);
//add a line break after each row
            CSV += row + '\r\n';
        }

        if (CSV == '') {
            alert("Invalid data");
            return;
        }

//this trick will generate a temp "a" tag
        var link = document.createElement("a");
        link.setAttribute('href', 'data:text/csv; charset=utf-8,');
        link.id = "lnkDwnldLnk";

//this part will append the anchor tag and remove it after automatic click
        document.body.appendChild(link);

        var csv = CSV;
        blob = new Blob([csv], {type: 'text/csv'});
        var csvUrl = window.webkitURL.createObjectURL(blob);
        var filename = ReportTitle + '.csv';
        $("#lnkDwnldLnk")
                .attr({
                    'download': filename,
                    'href': csvUrl
                });

        $('#lnkDwnldLnk')[0].click();
        document.body.removeChild(link);
    }
</script>
</body>
</html>