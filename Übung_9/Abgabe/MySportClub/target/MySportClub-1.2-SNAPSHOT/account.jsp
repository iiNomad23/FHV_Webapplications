<!DOCTYPE html>
<html lang="en">

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
            <li class="nav-item active">
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
            <h1>Create New Account</h1>
            <p>
                * fields are necessary
            </p>
        </div>
    </div>

    <!-- overview/main information -->
    <div class="container">
        <form action="accountRegistryHandler"
              method="post"
              onsubmit="return validateInputs()" novalidate>
            <div class="form-row">
                <div class="col-md mb-3">
                    <label for="firstName">First name *</label>
                    <input type="text" class="form-control" name="firstName" id="firstName" placeholder="John">
                    <div class="invalid-feedback">Please provide a valid first name.</div>
                </div>
                <div class="col-md mb-3">
                    <label for="lastName">Surname *</label>
                    <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Doe">
                    <div class="invalid-feedback">Please provide a valid last name.</div>
                </div>
            </div>

            <div class="form-row">
                <div class="col-md mb-3">
                    <label for="address">Address *</label>
                    <textarea class="md-textarea form-control" name="address" id="address" rows="3"></textarea>
                    <div class="invalid-feedback">Please provide a valid address.</div>
                </div>
                <div class="col-md mb-3">
                    <label for="country">Country *</label>
                    <select class="custom-select" name="country" id="country">
                        <option selected value="">Select..</option>

                        <option>Austria</option>
                        <option>Switzerland</option>
                        <option>Germany</option>
                        <option>others</option>
                    </select>
                    <div class="invalid-feedback">Please select a country.</div>
                </div>
            </div>

            <div class="form-group">
                <label>Gender *</label><br>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="gender" id="male" value="male" required>
                    <label class="form-check-label" for="male">Male</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="gender" id="female" value="female" required>
                    <label class="form-check-label" for="female">Female</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="gender" id="diverse" value="diverse" required>
                    <label class="form-check-label" for="diverse">Divers</label>
                </div>

                <div id="gender-feedback" class="invalid-feedback">Please select a gender.</div>
            </div>

            <hr>

            <div class="form-group">
                <label for="userID">Your User-ID *</label>
                <input type="text" class="form-control" name="userID" id="userID" placeholder="john.doe">
                <div class="invalid-feedback">Please provide a valid userID.</div>
            </div>
            <div class="form-row">
                <div class="col-md mb-3">
                    <label for="email">E-Mail *</label>
                    <input type="text" class="form-control" name="email" id="email" placeholder="john.doe@email.com">
                    <div class="invalid-feedback">Please provide a valid email.</div>
                </div>
                <div class="col-md mb-3">
                    <label for="mobileNumber">Mobile Number</label>
                    <input type="text" class="form-control" name="mobileNumber" id="mobileNumber">
                </div>
            </div>

            <div class="form-row">
                <div class="col-md mb-3">
                    <label for="password">Password *</label>
                    <input type="password" class="form-control" name="password" id="password">
                    <div class="invalid-feedback">Please provide a valid password.</div>
                </div>
                <div class="col-md mb-3">
                    <label for="confirmPassword">Confirm Password *</label>
                    <input type="password" class="form-control" id="confirmPassword">
                    <div class="invalid-feedback">Please provide a valid confirmation password.</div>
                </div>
            </div>

            <div class="form-group">
                <label for="sportCategories">Sport Categories *</label>
                <select multiple class="form-control" id="sportCategories" name="sportCategory">
                    <option>Climbing</option>
                    <option>Cycling</option>
                    <option>Friendly Fire</option>
                </select>
                <div class="invalid-feedback">Please select a sport category.</div>
            </div>

            <div class="form-group" id="user-category-checkboxes">
                <label>User Categories *</label><br>

                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" name="userCategory" id="athlete" value="athlete">
                    <label class="form-check-label" for="athlete">Athlete</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" name="userCategory" id="organizer"
                           value="organizer">
                    <label class="form-check-label" for="organizer">Organizer</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" name="userCategory" id="admin" value="admin">
                    <label class="form-check-label" for="admin">Admin</label>
                </div>

                <div id="sportCategories-feedback" class="invalid-feedback">Please select at least one user category.</div>
            </div>

            <button class="btn my-btn-secondary accountButton" type="reset">Reset Details</button>
            <button class="btn my-btn-primary accountButton" type="submit">Submit</button>
        </form>
    </div>
</main>

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
