var express    = require('express')
var bodyParser = require('body-parser')
var errorhandler = require('errorhandler')
var methodOverride = require('method-override')
var cors = require('cors')
var app = express()

// parse application/json
  app.use(bodyParser.urlencoded({ extended: false }))
  app.use(bodyParser.json())    
  app.use(methodOverride());
  app.use(cors());    // this was included
  app.use(errorhandler({ dumpExceptions: true, showStack: true }));

app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();
});

app.all(function (req, res, next) {
  data = req.query.json;
var stringify = JSON.stringify(data)
content = JSON.parse(stringify);  
console.log(content);
  res.statusCode(200)
  next()
})

app.listen(3000, function () {
  console.log('Example app listening on port 3000!')
})
