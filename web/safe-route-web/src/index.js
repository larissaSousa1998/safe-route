const express = require("express");
const app = express(); // create express app
const path = require("path");

app.use(express.static(path.join(__dirname, "..", "public")));

app.use(function (req, res, next) {

    // // Website you wish to allow to connect
    // res.setHeader('Access-Control-Allow-Origin', 'http://localhost:8888');

    // Request methods you wish to allow
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');

    // Request headers you wish to allow
    res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,content-type');

    // Set to true if you need the website to include cookies in the requests sent
    // to the API (e.g. in case you use sessions)
    res.setHeader('Access-Control-Allow-Credentials', true);

    // Pass to next layer of middleware
    next();
});


app.listen(3000,"172.31.95.172" ,console.log('listening on port 3000'));
// app.listen(3000, console.log("server listening on port 3000"));
