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

// get all IP addresses + date and time
main.get('/host-date-info', function (req, res) {
    const ips = [];

    let networkInterfaces = os.networkInterfaces();

    for (let key in networkInterfaces) {
        const networkInfoArr = networkInterfaces[key];

        if (networkInfoArr != null) {
            for (let j = 0; j < networkInfoArr.length; j++){
                const networkInfo = networkInfoArr[j];

                if (networkInfo != null) {
                    ips.push(networkInfo.address);
                }
            }
        }
    }

    res.json({
        ip: ips,
        date: new Date(),
        time: new Date().getTime()
    });
});


module.exports = main;