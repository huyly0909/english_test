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
        <title>Account</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/list_view_style.css">
        <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
        <script TYPE="text/javascript">
            function checkerRow() {
                $(".list-view-edit-btn").attr("style", $("input[id='rowChecker']:checked").length === 1 ? "" : "display: none")
            }
            
            function checkerHeader() {
                if ($("#headerChecker").is(":checked")) {
                    $('input[id="rowChecker"]').prop("checked", true);
                } else {
                    $('input[id="rowChecker"]').prop("checked", false)
                }
            }
            
            function addAction() {
                $("input[name='action']").val("add");
                $("form[name='listViewForm']").submit();
            }

            function deleteAction() {
                var ids = $("input[id='rowChecker']:checked").map(function() {
                    return this.value;
                }).toArray();
            }
            
        </script>
    </head>
    <body>
        ${topBtn}
        <form method="post" action="general_controller" name="listViewForm" onsubmit="return checkInput()">
<!--            <input type="hidden" value="" name="ids">
            <input type="hidden" value="" name="action">-->
            <input value="text" value="${type}" name="type">
            <input value="text" name="ids">
            <input type="text" value="" name="action">
        </form>
        <div class="test-form-container header">
            ${actionBtn}
            ${listHeader}
        </div>
        <div class="test-form-container body">
            ${listBody}
        </div>
    </body>
</html>
