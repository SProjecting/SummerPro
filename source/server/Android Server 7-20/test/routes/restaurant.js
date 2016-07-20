var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
router.use(bodyParser.json());
router.use(bodyParser.urlencoded({ extended: false }));

/* GET users listing. */
router.get('/upload', function(req, res) {
  res.render('restaurant/uploadRecipe');    // 菜谱上传 浏览
});
router.post('/upload', function(req, res) {
  upload(req, res);                         // 菜谱上传 提交
});

function upload(req, res){

  var name = req.body.name;
  var introduction = req.body.introduction;
  var major = req.body.major;
  var minor = req.body.minor;
  var method = req.body.method;
  var time = req.body.time;
  var procedure = req.body.procedure;
  var tip = req.body.tip;
  var flavour = req.body.flavour;
  var avoid = req.body.avoid;
  var price = req.body.price;
  var isRecommended = req.body.isRecommended;

  var connection = getConnection();

  var uploadSql = 'INSERT INTO dish(name, introduction, major_material, minor_material, method, time, procedure, tips, )';
  var upload_Params = [id];

  connection.query(uploadSql, upload_Params,function (error, rows, fields) {

    if(error) {
      throw error;
      pool.end();
      pool = null;
    }

    pool.end();
    pool = null;
    res.render('admin/businessDetail', {rows: rows});
  });
}

function getConnection() {

  var mysql  = require('mysql');
  var connection = mysql.createConnection({
    host     : '127.0.0.1',
    user     : 'root',
    password : 'xiannvzier',
    port: '3306',
    database: 'shiyoudb',
  });

  connection.connect(function(err){
    if(err){
      console.log('[query] - :'+err);
      return;
    }
    // console.log('[connection connect]  succeed!');
  });

  return connection;
}


module.exports = router;
