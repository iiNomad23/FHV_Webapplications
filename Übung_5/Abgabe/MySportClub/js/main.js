/**
 * Created by Marco 05/11/2021
 */

document.addEventListener("DOMContentLoaded", function (e) {
    if (document.images != null) {
        for (let i = 0; i < document.images.length; i++) {
            document.images[i].addEventListener("click", function () {
                openPopup(this);
            });
        }
    }
});

function openPopup(imgEl) {
    let winWidth = imgEl.width + 15;
    let winHeight = imgEl.height + 40;

    const y = window.top.outerHeight / 2 + window.top.screenY - (winHeight / 2);
    const x = window.top.outerWidth / 2 + window.top.screenX - (winWidth / 2);
    let newWindow = window.open("", "", "width=" + winWidth + ",height=" + winHeight + ",top=" + y + ", left=" + x);

    newWindow.document.write(
        "<head>" +
        "<title>Image</title>" +
        "</head>" +

        "<body>" +
        "<div>" +
        "<img src='" + imgEl.src + "' alt='" + imgEl.alt + "' style='width: " + imgEl.width + "; height: " + imgEl.height + "'/>" +
        "<input type='button' value='Close' onclick='self.close()'/>" +
        "</div>" +
        "</body>"
    );

    newWindow.onload = function () {
        newWindow.focus();
    };
}

function validateInputs() {
    let elObj = {
        firstNameEl: document.getElementById("firstName"),
        lastNameEl: document.getElementById("lastName"),
        addressEl: document.getElementById("address"),
        countryEl: document.getElementById("country"),
        userIDEl: document.getElementById("userID"),
        emailEl: document.getElementById("email"),
        passEl: document.getElementById("password"),
        confirmPassEl: document.getElementById("confirmPassword"),
        sportCatEl: document.getElementById("sportCategories"),
    };

    // default checks --------------------------------------------------------------------------------------------------
    if (!validateStandardElems([elObj.firstNameEl, elObj.lastNameEl, elObj.addressEl, elObj.countryEl])) {
        return false;
    }

    if (!validateRadios()) {
        return false;
    }

    if (!validateStandardElems([elObj.userIDEl, elObj.emailEl, elObj.passEl, elObj.confirmPassEl, elObj.sportCatEl])) {
        return false;
    }

    if (!validateCheckBoxes()) {
        return false;
    }


    // extended checks -------------------------------------------------------------------------------------------------
    if (!validateUserID(elObj.userIDEl)) {
        return false;
    }

    if (!validatePassword(elObj.passEl, elObj.confirmPassEl)) {
        return false;
    }

    if (!validateEmail(elObj.emailEl)) {
        return false;
    }

    return true;
}

//#region default check functions --------------------------------------------------------------------------------------
function validateStandardElems(elemArr) {
    for (let i = 0; i < elemArr.length; i++) {
        let el = elemArr[i];

        if (el.value == null || el.value === "") {
            setVisualValidation(el, false);
            el.focus();

            return false;
        }

        if (el.id !== "userID" && el.id !== "email" && el.id !== "password" && el.id !== "confirmPassword") {
            setVisualValidation(el, true);
        }
    }

    return true;
}

function validateRadios() {
    let radioElArr = document.querySelectorAll('input[name="gender"]');
    let isValid = document.querySelectorAll('input[name="gender"]:checked').length > 0;

    for (let i = 0; i < radioElArr.length; i++) {
        setVisualValidation(radioElArr[i], isValid);
    }

    setCustomValidation(document.getElementById("gender-feedback"), isValid);

    return isValid;
}

function validateCheckBoxes() {
    let checkElArr = document.querySelectorAll('input[name="userCategory"]');
    let isValid = document.querySelectorAll('input[name="userCategory"]:checked').length > 0;

    for (let i = 0; i < checkElArr.length; i++) {
        let el = checkElArr[i];

        if (isValid) {
            el.removeAttribute("required");
        } else {
            el.setAttribute("required", "");
        }

        setVisualValidation(el, isValid);
    }

    setCustomValidation(document.getElementById("sportCategories-feedback"), isValid);

    return isValid;
}

//#endregion

//#region extended check functions -------------------------------------------------------------------------------------
function validateString(str, regex) {
    return regex.exec(str) !== null;
}

function validateUserID(el) {
    let regex = /^[a-zA-Z_]{5,9}$/;
    let isValid = validateString(el.value, regex);

    setVisualValidation(el, isValid);

    return isValid;
}

function validatePassword(elPass, elPassConf) {
    let regex = /^[a-zA-Z][a-zA-Z0-9_]{7,8}$/;
    let isValid = validateString(elPass.value, regex) && elPass.value === elPassConf.value;

    setVisualValidation(elPass, isValid);
    setVisualValidation(elPassConf, isValid);

    return isValid;
}

function validateEmail(el) {
    // [a-zA-Z](\.?[\w-]+)*                 - name
    // @
    // (?=.{3,63}$)                         - look ahead
    // (([a-zA-Z](-*[\w]+)*)\.)*            - sub domains
    // [a-zA-Z](-*[a-zA-Z0-9])+\.           - domain name
    // [a-zA-Z]{2,4}                        - TLD
    let regex = /^[a-zA-Z](\.?[\w-]+)*@(?=.{3,63}$)(([a-zA-Z](-*[\w]+)*)\.)*[a-zA-Z](-*[a-zA-Z0-9])+\.[a-zA-Z]{2,4}$/;
    let isValid = validateString(el.value, regex);

    setVisualValidation(el, isValid);

    return isValid;
}

//#endregion

//#region visual feedback functions ------------------------------------------------------------------------------------
function setVisualValidation(el, isValid) {
    if (el == null) {
        return;
    }

    if (isValid) {
        el.classList.remove("is-invalid");
        el.classList.add("is-valid");
    } else {
        el.classList.remove("is-valid");
        el.classList.add("is-invalid");
    }
}

function setCustomValidation(el, isValid) {
    if (isValid) {
        el.classList.remove("displayBlock");
    } else {
        el.classList.add("displayBlock");
    }
}

//#endregion