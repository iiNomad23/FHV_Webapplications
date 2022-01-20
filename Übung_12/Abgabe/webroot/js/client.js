/**
 * Created by Marco 20/01/2022
 */

const backend = "http://localhost:8080";

$(document).ready(function () {
    const input = document.getElementById('textInput');
    const searchVideoBtn = document.getElementById('searchVideo');

    searchVideoBtn.addEventListener('click', () => {
        let url = backend + "/find-video";
        let data = {
            video: input.value
        };

        // with jquery ajax api
        $.ajax({
            url: url,
            type: "POST",
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                if (result != null) {
                    if (result.videoFound) {
                        requestSuccess();
                    } else {
                        requestError("Video not found!");
                    }
                } else {
                    requestError("Error Occurred!");
                }
            },
            error: function () {
                requestError("Error Occurred!");
            },
        });
    });
})

function requestSuccess() {
    const videoPlayer = document.getElementById('videoPlayer');
    const input = document.getElementById('textInput');

    videoPlayer.src = backend + "/video/" + input.value;
    videoPlayer.hidden = false;
    videoPlayer.autoplay = true;
}

function requestError(errMessage) {
    alert(errMessage);
}