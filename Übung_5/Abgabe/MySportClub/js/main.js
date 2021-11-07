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
    if (!validateUserID(textElemObj.userIDEl.value)) {
        return false;
    }

    if (!validatePassword(textElemObj.passwordEl.value, textElemObj.confirmPasswordEl.value)) {
        return false;
    }

    if (!validateEmail(textElemObj.emailEl.value)) {
        return false;
    }

    return true;
}

//#region default checks functions -------------------------------------------------------------------------------------
function validateTextInputs(elemObj) {
    for (const elemObjKey in elemObj) {
        let el = elemObj[elemObjKey];

        if (el.value == null || el.value === "") {
            return false;
        }
    }

    return true;
}

function validateSelectInputs(elemObj) {
    for (const elemObjKey in elemObj) {
        let el = elemObj[elemObjKey];

        if (el.value == null || el.value === "") {
            return false;
        }
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

function validateUserID(userID) {
    let regex = /^[a-zA-Z_]{5,9}$/;

    return validateString(userID, regex);
}

function validatePassword(password, passwordConfirmation) {
    let regex = /^[a-zA-Z][a-zA-Z0-9_]{8,9}$/;

    if (!validateString(password, regex)) {
        return false;
    }

    return password === passwordConfirmation;
}

function validateEmail(mail) {
    let regex = /^[a-zA-Z](\.?[\w-]+)*@(([a-zA-Z](-*[\w]+)*)\.)*[a-zA-Z](-*[a-zA-Z0-9]){3,63}\.[a-zA-Z]{2,4}$/;
    return validateString(mail, regex);
}

//#endregion