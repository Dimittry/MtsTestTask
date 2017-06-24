<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title></title>

</head>
<body>
    <div class="container">
        <div class="header clearfix">
            <h3 class="text-muted">MTS Test Task</h3>
        </div>
        <div class="btn-group" style="margin-bottom: 15px;">
            <a href="${contextPath}/employee" title="Добавить сотрудника" type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Добавить сотрудника
            </a>
        </div>
        <c:choose>
            <c:when test="${not empty param.order}">
                <c:set var="href1" value="${contextPath}/"/>
                <c:set var="href2" value="${contextPath}/"/>
            </c:when>
            <c:when test="${not empty param.sort}">
                <c:set var="href1" value="?sort=name1&order=desc"/>
                <c:set var="href2" value="?sort=name2&order=desc"/>

            </c:when>
            <c:otherwise>
                <c:set var="href1" value="?sort=name1"/>
                <c:set var="href2" value="?sort=name2"/>
            </c:otherwise>
        </c:choose>
        <table class="table table-striped">
            <tr>
                <th>ID</th>
                <th><a href='<c:out value="${href1}"/>'>Name1</a></th>
                <th><a href='<c:out value="${href2}"/>'>Name2</a></th>
                <th></th>
            </tr>
            <c:forEach items="${employees}" var="employee">
                <tr class="employees-row">
                    <td>${employee.id}</td>
                    <td>${employee.name1}</td>
                    <td>${employee.name2}</td>
                    <td class="employees-buttons">
                        <div class="btn-group">
                            <a data-id="${employee.id}" class="view-employee" href="javascript:void(0);" title="Просмотр карточки" type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="glyphicon glyphicon glyphicon-zoom-in" aria-hidden="true"></span>
                            </a>
                        </div>
                        <div class="btn-group">
                            <a href="${contextPath}/employee/${employee.id}/update" title="Редактировать сотрудника" type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            </a>
                        </div>
                        <div class="btn-group">
                            <a href="${contextPath}/employee/${employee.id}/delete" title="Удалить сотрудника" type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="dialog" title="Просмотр сотрудника"></div>
    <div id="overlay"></div>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        var EmployeeViewer = (function () {
            var configMap = {
                ids : {
                    dialogId : "dialog",
                    overlayId : "overlay"
                },
                classes : {
                    showLink : "view-employee"
                }
            };
            var jqueryMap = {
                showLink : null,
                dialog : null,
                overlay : null
            };

            var run = function () {
                _setJqueryMap();
                _initDialog();
                _listenShowLinkClick();
            };

            var _setJqueryMap = function () {
                  jqueryMap = {
                      showLink : $("." + configMap.classes.showLink),
                      dialog : $("#" + configMap.ids.dialogId),
                      overlay : $("#" + configMap.ids.overlayId)
                  };
            };

            var _initDialog = function () {
                if(!jqueryMap.dialog) throw "Empty dialog window.";

                (jqueryMap.dialog).dialog({
                    autoOpen: false,
                    show: {
                        effect: "blind",
                        duration: 1000
                    },
                    close : function () {
                        jqueryMap.overlay.hide();
                    }
                });
            };
            
            var _listenShowLinkClick = function () {
                $("body").on("click", "."+configMap.classes.showLink, function (e) {
                    var employeeId = $(this).data("id");
                    $.get("/api/employee/"+employeeId, function (data) {
                        if(data) {
                            _fillDialogWindow(data);
                            (jqueryMap.dialog).dialog("open");
                            console.log(jqueryMap);
                            jqueryMap.overlay.show();
                        }
                    }, "json");
                    e.preventDefault();
                    return false;
                });
            }

            var _fillDialogWindow = function (data) {
                var html = "";
                html += "<p>" + data.id + "</p>";
                html += "<p>" + data.name1 + "</p>";
                html += "<p>" + data.name2 + "</p>";
                jqueryMap.dialog.html(html);
            };

            return {
                run : run
            };
        })();
        EmployeeViewer.run();
    </script>
    <style>
        #overlay{
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: 10;
            display: none;
            background-color: rgba(0,0,0,0.5); /*dim the background*/
        }
    </style>
</body>
</html>