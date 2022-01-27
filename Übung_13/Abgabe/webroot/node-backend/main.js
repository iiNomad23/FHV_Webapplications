/**
 * Created by Marco 27/01/2022
 */

const express = require('express');
const path = require('path');
const os = require('os');
const cors = require('cors');

const main = express();

main.use(express.static(path.join(__dirname, 'public')));
main.use(cors({origin: '*'}));

// get all IP addresses & send response with date and time
main.get('/host-date-info', function (req, res) {
    const ips = [];
    for (let i = 0; i < Object.keys(os.networkInterfaces()).length; i++){
        const netInf = Object.keys(os.networkInterfaces())[i];

        for (let j = 0; j < os.networkInterfaces()[netInf].length; j++){
            const currInf = os.networkInterfaces()[netInf][j];
            ips.push(currInf.address);
        }
    }

    res.json({
        ip: ips,
        date: new Date(),
        time: new Date().getTime()
    });
});


module.exports = main;