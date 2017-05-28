// grab the packages we need
var express = require('express');
var mysql = require('mysql');
var app = express();
var port = process.env.PORT || 3000;


var connection = mysql.createConnection({
    host: 'localhost',
    user: 'tahel',
    password: 'tahelmeni',
    database: 'collect_test_1'
})


connection.connect()



var bodyParser = require('body-parser');
app.use(function(req, res, next) {

    // Website you wish to allow to connect
    res.setHeader('Access-Control-Allow-Origin', '*');

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
app.use(bodyParser.json()); // support json encoded bodies
app.use(bodyParser.urlencoded({
    extended: true
})); // support encoded bodies


// routes will go here

// start the server
app.listen(port);


app.get('/getDestination', function(req, res) {
    res.setHeader('Content-Type', 'application/json');

    connection.query('SELECT * FROM destination', function(err, rows, fields) {
        if (err) throw err

        res.jsonp(rows);
    })

});

app.get('/getlist', function(req, res) {
    res.setHeader('Content-Type', 'application/json');
    // res.jsonp({
    //     user: 'tobi'
    // });

    connection.query('SELECT * FROM items', function(err, rows, fields) {
        if (err) throw err

        res.jsonp(rows);
    })

});

app.post('/send/', function(req, res) {
    data = req.body;
    var stringify = JSON.stringify(data)
    content = JSON.parse(stringify);
    // console.log(content);
    // console.log(content.details.destination);

    // connection.query('SELECT * FROM items', function(err, rows, fields) {
    //     if (!err)
    //         console.log('the solution is: ', rows);
    //     else
    //         console.log('error mysql');
    // });

    var post = {
        destination_id: parseInt(content.details.destination),
        age: parseInt(content.details.age),
        gender: content.details.gender,
        period: parseInt(content.details.period),
        other_items: content.otherItem
    };
    console.log(post);
    var query = connection.query('INSERT INTO users SET ?', post, function(err, res) {
        if (!err)
            console.log('new user add ' + res.insertId);
        else
            console.log('error mysql');

        console.log(content.list);
        content.list.forEach(function (item) {
            var item_post = {
                user_id: res.insertId,
                item_id: parseInt(item)
            };
            console.log(item);
            var item_query = connection.query('INSERT INTO user_items SET ?', item_post, function(err, sqlres) {
                if (err)
                    console.log('user_items mysql error');
            });
        });
        // for (item in content.list) {
        //     var item_post = {
        //         user_id: res.insertId,
        //         item_id: parseInt(item)
        //     };
        // console.log(item);
        //     var item_query = connection.query('INSERT INTO user_items SET ?', item_post, function(err, sqlres) {
        //         if (err)
        //             console.log('user_items mysql error');
        //     });
        // }

        // console.log(query.sql);

    });


    // connection.end();
    res.sendStatus(200);
});
console.log('Server started! At http://localhost:' + port);
