/**
 * Created by Marco 05/11/2021
 */


function validateInputs() {
    let textElemObj = {
        firstNameEl: document.getElementById("firstName"),
        lastNameEl: document.getElementById("lastName"),
        addressEl: document.getElementById("address"),
        userIDEl: document.getElementById("userID"),
        emailEl: document.getElementById("email"),
        passwordEl: document.getElementById("password"),
        confirmPasswordEl: document.getElementById("confirmPassword"),
    };

    let selectElemObj = {
        countryEl: document.getElementById("country"),
        sportCategoriesEl: document.getElementById("sportCategories"),
    };

    // default checks --------------------------------------------------------------------------------------------------
    if (!validateTextInputs(textElemObj)) {
        return false;
    }

    if (!validateSelectInputs(selectElemObj)) {
        return false;
    }

    if (!validateRadios()) {
        return false;
    }

    if (!validateCheckBoxes()) {
        return false;
    }


    // extended checks -------------------------------------------------------------------------------------------------
    if (!validateUserID(textElemObj.userIDEl)) {
        return false;
    }

    if (!validatePassword(textElemObj.passwordEl, textElemObj.confirmPasswordEl)) {
        return false;
    }

    if (!validateEmail(textElemObj.emailEl)) {
        return false;
    }

    return true;
}

//#region default checks functions -------------------------------------------------------------------------------------
function validateTextInputs(elemObj) {
    for (const elemObjKey in elemObj) {
        let el = elemObj[elemObjKey];

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

function validateSelectInputs(elemObj) {
    for (const elemObjKey in elemObj) {
        let el = elemObj[elemObjKey];

        if (el.value == null || el.value === "") {
            setVisualValidation(el, false);
            el.focus();

            return false;
        }

        setVisualValidation(el, true);
    }

    return true;
}

function validateRadios() {
    let radioEls = document.querySelectorAll('input[name="gender"]');

    let checked = false;
    for (const el of radioEls) {
        if (el.checked) {
            checked = true;
            break;
        }
    }

    return checked;
}

function validateCheckBoxes() {
    return document.querySelectorAll('input[name="userCategory"]:checked').length > 0;
}

//#endregion

//#region extended checks functions ------------------------------------------------------------------------------------
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
    let regex = /^[a-zA-Z][a-zA-Z0-9_]{8,9}$/;

    let isValid = validateString(elPass.value, regex) && elPass.value === elPassConf.value;
    setVisualValidation(elPass, isValid);
    setVisualValidation(elPassConf, isValid);

    return isValid;
}

function validateEmail(el) {
    let regex = /^[a-zA-Z](\.?[\w-]+)*@(([a-zA-Z](-*[\w]+)*)\.)*[a-zA-Z](-*[a-zA-Z0-9]){3,63}\.[a-zA-Z]{2,4}$/;
    
    let isValid = validateString(el.value, regex);
    setVisualValidation(el, isValid);

    return isValid;
}

//#endregion

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