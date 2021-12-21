/**
 * Created by Marco 21/12/2021
 */

$(document).ready(function () {
    let $textInput = $("#textInput");
    let $textCounter = $("#textCounter");

    $textInput.on('input', function (event) {
        let data = {
            string: $textInput.val(),
        }

        fetch('http://localhost:8080', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            // Set textCounter value according to response
            .then(data => $textCounter.text(data.characterCount))
            .catch(() => $textCounter.text("Error Occurred!"));
    });
});
