let http = require('http');

const server = http.createServer(function (req, res) {
    // set headers to allow CORS requests
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Request-Method', '*');
    res.setHeader('Access-Control-Allow-Methods', 'OPTIONS, POST');
    res.setHeader('Access-Control-Allow-Headers', '*');

    if (req.method === 'OPTIONS') {
        res.writeHead(200);
        res.end();
    } else if (req.method === 'POST') { // Actual count request handler
        res.writeHead(200, {'Content-Type': 'application/json'});

        let body = null;
        req
            .on('data', chunk => {
                body = chunk;
            })
            .on('end', () => {
                let parsedJSON = JSON.parse(body);
                if (parsedJSON.hasOwnProperty("string")) {
                    res.end(JSON.stringify({characterCount: parsedJSON.string.length}));
                }
            });
    } else {
        res.statusCode = 405;
        res.end();
    }
});

server.listen(8080);
console.debug('Server running at http://127.0.0.1:8080/');