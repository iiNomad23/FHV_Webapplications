<%--
  Created by IntelliJ IDEA.
  User: marco
  Date: 25/11/2021
  Time: 14:41
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">

    <meta name="HandheldFriendly" content="true"/>
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="keywords" content="MySportClub, Activities, Climbing, Cycling, Friendly Fire, Manfred Hiller">
    <meta name="robots" content="index, follow">
    <meta name="description" content="Search and find famous teams and tournaments.">
    <meta http-equiv="cache-control" content="no-cache, must-revalidate">

    <title>MySportClub</title>

    <!-- ############ import css files ########################################################################################################################### -->

    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/font-awesome.min.css">

    <link rel="stylesheet" href="./css/layout.css">
    <link rel="stylesheet" href="./css/print.css">

    <!-- ############ import js lib files ######################################################################################################################## -->

    <script src="./js/jquery-3.6.0.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>

    <!-- ############ import js src files ######################################################################################################################## -->

    <script src="./js/cookieHandler.js"></script>
    <script src="./js/main.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-myColor">
    <a class="navbar-brand" href="home.html">
        <i class="fa fa-soccer-ball-o"></i>
        MySportClub
    </a>

    <!-- navbar toggler is only shown if page width is small enough (jquery needed) -->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar"
            aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <!-- standard nav bar for normal desktop sizes -->
    <div class="collapse navbar-collapse" id="navbar">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="home.html">Home</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="loginHandler?dispatchto=./clubs/climbing_club.html">Climbing</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="loginHandler?dispatchto=./clubs/cycling_club.html">Cycling</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="loginHandler?dispatchto=./clubs/friendlyFire_eSport_club.html">Friendly Fire</a>
            </li>
        </ul>

        <!-- guestbook, registry and eMail-->
        <ul class="navbar-nav right">
            <li class="nav-item active">
                <a class="nav-link" href="./tableGenerator.jsp">FunHouse</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="guestbook_overview">Guestbook</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="./account.jsp">Registry</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="mailto:thisIsAEmail@hotmail.com">
                    <i class="fa fa-envelope-o" aria-hidden="true"></i>
                    Contact
                </a>
            </li>
        </ul>
    </div>
</nav>

<main role="main">

    <!-- header -->
    <div class="jumbotron my-jumbotron-sub">
        <div class="container">
            <h1>Table Generator</h1>
            <p>
                Here you can test and create some dynamic tables. Have fun!
            </p>
        </div>
    </div>

    <!-- overview/main information -->
    <div class="container">
        <form class='needs-validation' name="tableGenerator" action="tableGenerator.jsp">
            <div class="form-row">
                <div class="col-md mb-3">
                    <label for="rows">Rows</label>
                    <input type="number" class="form-control" min="1" max="16" name="rows" id="rows"
                           placeholder="number of rows" required>
                </div>
                <div class="col-md mb-3">
                    <label for="columns">Columns</label>
                    <input type="number" class="form-control" min="1" max="16" name="columns" id="columns"
                           placeholder="number of columns" required>
                </div>
            </div>

            <button class="btn my-btn-primary" type="submit">Create</button>
        </form>

        <hr>

        <%
            String[] colorArray = {"00", "11", "22", "33", "44", "55", "66", "77", "88", "99", "AA", "BB", "CC", "DD", "EE", "FF"};

            String rowsInput = request.getParameter("rows");
            String columnsInput = request.getParameter("columns");

            if (rowsInput != null && !rowsInput.equals("") && columnsInput != null && !columnsInput.equals("")) {
                int rows = Integer.parseInt(request.getParameter("rows"));
                int columns = Integer.parseInt(request.getParameter("columns"));

                if (rows > 0 && columns > 0) {
        %>
        <table class="table">
            <% for (int i = 0; i < rows; i++) { %>
            <tr>
                <% for (int j = 0; j < columns; j++) { %>
                <td class="colorTable" style="background-color: #AA<%= colorArray[i] + colorArray[j] %>">
                    <%= i + 1 %>-<%= j + 1 %>
                </td>
                <% } %>
            </tr>
            <% } %>
        </table>
        <% }
        } else {%>
        <div class="emptyArea">
            <span><b>Table Area</b></span>
        </div>
        <% } %>
    </div>

</main>

<!-- footer -->
<footer class="container">
    <br><br>
    <hr>

    <p>
        &copy; MySportClub 2021
        <span class="versionNum">v1.2</span>
    </p>
</footer>

</body>
</html>
