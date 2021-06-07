<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Список клиентов</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
    <link href="<c:url value="/css/background.css" />" rel="stylesheet">
    <link href="<c:url value="/css/nav.css" />" rel="stylesheet">
    <link href="<c:url value="/css/list.css" />" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Система Тестирования</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown active">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Тесты
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <form class="form-inline my-2 my-lg-0 dropdown-item" action="<c:url value="/system_of_testing"/>" method="GET">
                        <input type="hidden" name="command" value="to_tests_list"/>
                        <button type="submit" class="btn btn-link btn-thin dropdown-item">Тесты</button>
                    </form>
                    <form class="form-inline my-2 my-lg-0 dropdown-item" action="<c:url value="/system_of_testing"/>" method="GET">
                        <input type="hidden" name="command" value="to_add_test"/>
                        <button type="submit" class="btn btn-link btn-thin dropdown-item">Добавить Тест</button>
                    </form>
                </div>
            </li>
            <li class="nav-item">
                <form class="form-inline my-2 my-lg-0" action="<c:url value="/system_of_testing"/>" method="GET">
                    <input type="hidden" name="command" value="to_quests_list"/>
                    <button type="submit" class="btn btn-link nav-link active">вопросы</button>
                </form>
            </li>
            <li class="nav-item">
                <form class="form-inline my-2 my-lg-0" action="<c:url value="/system_of_testing"/>" method="GET">
                    <input type="hidden" name="command" value="to_answers_list"/>
                    <button type="submit" class="btn btn-link nav-link active">ответы</button>
                </form>
            </li>

        </ul>
        <form class="form-inline my-2 my-lg-0" action="<c:url value="/system_of_testing"/>" method="GET">
            <input type="hidden" name="command" value="logout"/>
            <button type="submit" class="btn btn-outline-light my-2 my-sm-0">Выйти из системы</button>
        </form>
    </div>
</nav>
<!--/////////////////////// конец шапки /////////////////////////////-->
<div class="card bg-dark text-white form-client-list">
    <div class="card-header">
        Список ответов
    </div>
    <div class="card-body">
        <table class="table table-light table-stripped" id="answers">
            <thead class="thead-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">текст ответа</th>
                <th scope="col">истинность ответа</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${answers}" var="item" varStatus="currentNumber">
                <tr>
                    <th scope="row"><c:out value="${currentNumber.count}"/></th>
                    <td><c:out value="${item.textOfAnswer}"/></td>
                    <td><c:out value="${item.answerIsTrue}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


    </div>
</div>
</body>
<script>
    $(document).ready(function () {
        $('#tests').dataTable();
    });
</script>
</html>