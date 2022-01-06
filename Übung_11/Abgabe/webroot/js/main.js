/**
 * Created by Marco 21/12/2021
 */

$(document).ready(function () {
    let $textInput = $("#textInput");

    $textInput.on('input', function (event) {
        let data = {
            string: $textInput.val(),
        }

        fetch('http://localhost:8080', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        }).then(response => {
                response.json()
                    // set textCounter value according to response
                    .then((result) => {
                        if (result != null) {
                            requestSuccess(result);
                        }
                    })
                    .catch(() => {
                        requestError("Error Occurred!");
                    });
            }
        );

        // with jquery ajax api
        // $.ajax({
        //     url: 'http://localhost:8080',
        //     type: "POST",
        //     contentType: 'application/json',
        //     data: JSON.stringify(data),
        //     success: function (result) {
        //         if (result != null) {
        //             requestSuccess(result);
        //         }
        //     },
        //     error: function () {
        //         requestError("Error Occurred!");
        //     },
        // });
    });
});

function requestSuccess(data) {
    $("#textCounter").text(data.characterCount || 0);
}

function requestError(errText) {
    $("#textCounter").text(errText || "Error");
}
