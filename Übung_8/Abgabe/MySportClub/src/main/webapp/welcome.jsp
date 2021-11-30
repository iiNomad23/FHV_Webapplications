<%--
  Created by IntelliJ IDEA.
  User: marco
  Date: 30/11/2021
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">

    <meta name="HandheldFriendly" content="true"/>
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="keywords" content="MySportClub, Activities, Climbing, Cycling, Friendly Fire, Manfred Hiller">
    <meta name="robots" content="index, follow">
    <meta name="description" content="Search and find famous teams and tournaments.">

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
                <a class="nav-link" href="./clubs/climbing_club.html">Climbing</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="./clubs/cycling_club.html">Cycling</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="./clubs/friendlyFire_eSport_club.html">Friendly Fire</a>
            </li>
        </ul>

        <!-- guestbook, registry and eMail-->
        <ul class="navbar-nav right">
            <li class="nav-item">
                <a class="nav-link" href="./tableGenerator.jsp">FunHouse</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="guestbook_overview">Guestbook</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="account.jsp">Registry</a>
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
            <h1>Welcome</h1>
        </div>
    </div>

    <!-- overview/main information -->
    <div class="container">
        <c:choose>
            <c:when test="${sessionScope.registry.validLogin}">
                <h1 class="display-3"><c:out value="${sessionScope.registry.userId}"/></h1>
            </c:when>
            <c:otherwise>
                <h1 class="display-3">Something went wrong, please retry your registry</h1>
            </c:otherwise>
        </c:choose>
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
