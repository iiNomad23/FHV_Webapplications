/**
 * Created by Marco 20/01/2022
 */

const express = require('express');
const bodyParser = require('body-parser');
const path = require('path');
const fs = require('fs');

const main = express();

main.use(bodyParser.json())
main.use(express.static(path.join(__dirname, 'public')));

// get all videos from folder path
const videoFolderPath = './video/';
const videoList = [];
fs.readdir(videoFolderPath, (err, files) => {
    files.forEach(video => {
        videoList.push(video);
    });
});

main.post('/find-video', function (req, res) {
    res.json({videoFound: videoList.includes(req.body.video)});
});

// Source: https://dev.to/abdisalan_js/how-to-code-a-video-streaming-server-using-nodejs-2o0
main.get('/video/*', function (req, res) {
    // Ensure there is a range given for the video
    const range = req.headers.range;
    if (!range) {
        res.status(400).send("Requires Range header");
    }

    // get video stats (about 61MB)
    const videoPath = "." + req.url;
    const videoSize = fs.statSync(videoPath).size;

    // Parse Range
    // Example: "bytes=32324-"
    const CHUNK_SIZE = 10 ** 6; // 1MB
    const start = Number(range.replace(/\D/g, ""));
    const end = Math.min(start + CHUNK_SIZE, videoSize - 1);

    // Create headers
    const contentLength = end - start + 1;
    const headers = {
        "Content-Range": `bytes ${start}-${end}/${videoSize}`,
        "Accept-Ranges": "bytes",
        "Content-Length": contentLength,
        "Content-Type": "video/mp4",
    };

    // HTTP Status 206 for Partial Content
    res.writeHead(206, headers);

    // create video read stream for this particular chunk
    const videoStream = fs.createReadStream(videoPath, { start, end });

    // Stream the video chunk to the client
    videoStream.pipe(res);
});

module.exports = main;