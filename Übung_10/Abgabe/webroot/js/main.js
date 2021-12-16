/**
 * Created by Marco 14/12/2021
 */

$(document).ready(function () {
    let map = L.map('map').setView([0, 0], 2);
    let eventMarkers = [];
    let eventMarkerCoordinates = [];

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
    }).addTo(map);

    // fetch events from XML using rss2json
    $.get('https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fwww.festivalticker.de%2Frss-festivalfeed%2Ffestivalkalender1-60.xml', function (response) {

        // create HTML elements based on response items
        $.each(response.items, function (index, item) {

            const regExp = /Ort: \d+ ([\wöäüÖÄÜß ]*)<br>Land: ([\wöäüÖÄÜß ]*)/gm;
            const match = regExp.exec(item.description);

            // create element
            let html = "";
            html += "<div id='events_" + index + "'>";
            html += "<h2>" + item.title + "</h2>";
            html += "<p>" + item.description + "</p>";
            html += "</div><hr>";

            $('#events').append(html);

            // add events
            let $event = $("#event_" + index);
            if (match) {
                $event.on('click', function () {
                    // if not cached yet, save it in local variable
                    if (eventMarkers[index] == null || eventMarkerCoordinates[index] == null) {
                        addEventMarker(map, eventMarkers, eventMarkerCoordinates, index, item.title, {
                            city: match[1],
                            country: match[2]
                        });
                    } else {
                        eventMarkers[index].openPopup();
                        map.flyTo(eventMarkerCoordinates[index], 12);
                    }
                });
            } else {
                $event.on("click", function () {
                    let $this = $(this);

                    $this.css('background-color', '#ff958c')
                    setTimeout(() => {
                        $this.css('background-color', '');
                    }, 250);
                });
            }
        });
    });
});

function addEventMarker(map, eventMarkers, eventMarkerCoordinates, eventId, title, location) {
    let url = "https://nominatim.openstreetmap.org/search?q=" + location.city + "%20" + location.country + "&limit=1&format=json&addressdetails=1";

    $.get(url, function (response) {

        // get coordinates from response
        let mapCoords = response[0];

        if (mapCoords != null) {
            const latitude = mapCoords.lat;
            const longitude = mapCoords.lon;

            const eventMarker = L.marker([latitude, longitude]).addTo(map);

            eventMarker.bindPopup("<b>" + title + "</b>").openPopup();
            map.flyTo([latitude, longitude], 12);

            // save marker and marker coordinates for later usage
            eventMarkers[eventId] = eventMarker;
            eventMarkerCoordinates[eventId] = [latitude, longitude];
        }
    });
}