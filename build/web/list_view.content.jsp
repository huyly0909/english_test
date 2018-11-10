<%-- 
    Document   : list_view.content
    Created on : Oct 27, 2018, 11:22:18 AM
    Author     : tinluu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List View</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/list_view_style.css">
        <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
        <script TYPE="text/javascript">
            function checkerRow() {
                $(".list-view-edit-btn").attr("style", $("input[id='rowChecker']:checked").length === 1 ? "" : "display: none")
            }
            
            function edit(id) {
                var testsArr = ["Reading", "Writing", "Listening", "Speaking"];
                $("input[name='ids']").attr("value", id);
                var type = '${type}';
                if(testsArr.indexOf(type)) {
                    $("form[name='listViewForm']").attr("action", "test");
                }
                $("input[name='action']").attr("value", "do" + type); // doReading, doWriting...
                $("form[name='listViewForm']").submit();
            }
            
            function checkerHeader() {
                if ($("#headerChecker").is(":checked")) {
                    $('input[id="rowChecker"]').prop("checked", true);
                } else {
                    $('input[id="rowChecker"]').prop("checked", false);
                }
            }
            
            function addAction() {
                $("input[name='action']").attr("value", "add");
                $("form[name='listViewForm']").submit();
            }

            function deleteAction() {
                var ids = $("input[id='rowChecker']:checked").map(function() {
                    return this.value;
                }).toArray();
                if(ids.length === 0) {
                    alert("Select at least one row to delete.");
                } else {
                    $("input[name='action']").attr("value", "delete");
                    $("input[name='ids']").attr("value", ids);
                    $("form[name='listViewForm']").submit();
                }
            }
            
        </script>
    </head>
    <body>
        ${topBtn}
        <form method="post" action="general_controller" name="listViewForm">
            <input type="text" value="${type}" name="type">
            <input type="text" value="${questionType}" name="question_type">
            <input type="text" name="ids">
            <input type="text" value="" name="action">
        </form>
        <div class="test-form-container header">
            ${listHeader}
            ${actionBtn}
        </div>
        <div class="test-form-container body">
            ${listBody}
        </div>
    </body>
</html>
