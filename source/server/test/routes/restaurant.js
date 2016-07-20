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

router.get('/recipeList', function(req, res) {
  // res.render('restaurant/recipeList');   // 菜谱列表
  if(req.session.username)
    getRecipeList(req, res);
  else
    res.redirect('/login');      
  
});
router.get('/recipeDetails*', function(req, res) {
  getRecipeDetails(req, res);                  // 菜谱详情
});
router.post('/delete', function(req, res) {
  deleteRecipe(req, res);                  // 删除菜谱
});
router.get('/modifyRecipe*', function(req, res) {
  res.locals.username = req.session.username;
  
  modifyRecipe(req, res);                  // 修改菜谱
});
router.post('/modifyRecipe', function(req, res) {
  modifyRecipeSubmit(req, res);            // 修改菜谱
});

router.get('/main', function(req, res) {
  res.render('restaurant/main');           // 商家主页
});
router.get('/comment', function(req, res) {
  if(req.session.username)                 // 评论列表
    getComment(req, res);
  else
    res.redirect('/login');

  // res.render('restaurant/comment');
});

router.get('/modify', function(req, res) {
  if(req.session.username)                 // 修改商家信息
      getRestaurantInfo(req, res);
    // res.render('restaurant/modifyRestaurant');
  else
    res.redirect('/login');
});
router.post('/modify', function(req, res) {
  modifyRestaurant(req, res);              // 修改商家信息
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

  // 插入dish表
  var dish_id;
  var connection = getConnection();
  var uploadSql = 'INSERT INTO dish(name, introduction, major_material, minor_material, method, time, process, tips, flavour, ' +
      'avoid, price, isRecommend) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)';
  var upload_Params = [name, introduction, major, minor, method, time, procedure, tip, flavour, avoid, price, isRecommended];

  connection.query(uploadSql, upload_Params,function (error, result) {

    if(error) {
      throw error;
      res.send('上传失败！');
      connection.end();
      return ;
    }

    dish_id = result.insertId;

    // 插入 menu 表
    var menuSql = 'INSERT INTO menu VALUES(?, ?)';   // restaurant_id dish_id
    var menu_Params = [24, dish_id];
    connection.query(menuSql, menu_Params,function (err, result){
      if(err){
        throw error;
        connection.end();
        res.send('上传失败！');
        return ;
      }

      connection.end();
      res.send('上传成功！');
    } );
    
  });
}

function getRecipeList(req, res){

  var id = req.session.userId;

  var querySql = 'SELECT * FROM dish WHERE id IN( SELECT dish_id FROM menu WHERE restaurant_id = ?) AND del = ?';
  var query_Params = [id, 0];

  var connection = getConnection();
  connection.query(querySql, query_Params,function (error, rows, fields) {

    if(error) {
      throw error;
      connection.end();
    }

    connection.end();
    res.render('restaurant/recipeList', {rows: rows});
  });
}

function getRecipeDetails(req, res){

  var id = req.query.id;
  // console.log(id);

  var querySql = 'SELECT * FROM dish WHERE id = ?';
  var query_Params = [id];

  var connection = getConnection();

  connection.query(querySql, query_Params,function (error, rows, fields) {

    if(error) {
      throw error;
      connection.end();
    }

    connection.end();
    res.render('restaurant/recipeDetails', {rows: rows});
  });
}

function deleteRecipe(req, res){

  var id = req.body.id;

  var connection = getConnection();
  var updateSql = 'UPDATE dish SET del = 1 WHERE id = ?';
  var updateSql_Params = [id];
  connection.query(updateSql, updateSql_Params, function (err, result) {

    if (err) {
      throw err;
      connection.end();
      res.send('删除失败！');
      return ;
    }

    console.log('deleted ' + result.affectedRows + ' rows');
    connection.end();
    res.send('删除成功！');

  })
}

function modifyRecipe(req, res){

  var id = req.query.id;

  var querySql = 'SELECT * FROM dish WHERE id = ?';
  var query_Params = [id];

  var connection = getConnection();

  connection.query(querySql, query_Params,function (error, rows, fields) {

    if(error) {
      throw error;
      connection.end();
    }

    connection.end();
    res.render('restaurant/modifyRecipe', {rows: rows, username : req.session.username});
  });

}

function getComment(req, res){

  var id = req.session.userId;

  var querySql = 'SELECT * FROM comment WHERE restaurant_id = ?';
  var querySql_Params = [id];

  var connection = getConnection();
  connection.query(querySql, querySql_Params,function (error, rows, fields){
    if(error){
      throw error;
      connection.end();
      return ;
    }
    
    res.render('restaurant/comment', { rows : rows, username : req.session.username});
  });
}

function getRestaurantInfo(req, res){
  var id = req.session.userId;

  var getInfoSql = 'SELECT * FROM restaurant WHERE id = ?';
  var getInfoSql_Params = [id];
  
  var connection = getConnection();
  connection.query(getInfoSql, getInfoSql_Params, function (error, rows, fields){
    if(error){
      throw error;
      connection.end();
      return ;
    }
    
    res.render('restaurant/modifyRestaurant', { rows : rows, username : req.session.username});
  });
}

function modifyRestaurant(req, res){

  var id = req.session.userId;
  var res_name = req.body.res_name;
  var introduction = req.body.introduction;
  var manager_name = req.body.manager_name;
  var manager_idnum = req.body.manager_idnum;
  var phone = req.body.phone;
  var email = req.body.email;
  var address = req.body.address;
  var business_hour = req.body.business_hour;

  var connection = getConnection();
  var modifySql = 'UPDATE restaurant SET res_name = ?, email = ?, phone = ?, address = ?, introduction = ?, business_hour = ?, ' +
      'manager_name = ?, manager_idnum = ? WHERE id = ?';
  var modifySql_Params = [res_name, email, phone, address, introduction, business_hour, manager_name, manager_idnum, id];
  connection.query(modifySql, modifySql_Params, function (err, result){
   
    if(err){
      throw err;
      connection.end();
      res.send('修改失败！');
      return ;
    }

    // console.log('修改 ' + result.affectedRows);
    connection.end();
    res.send('修改成功');
    
  });

}

function modifyRecipeSubmit(req, res){

  var id = req.body.id;
  var introduction = req.body.introduction;
  var major = req.body.major;
  var minor = req.body.minor;
  var email = req.body.email;
  var method = req.body.method;
  var time = req.body.time;
  var procedure = req.body.procedure;
  var tips = req.body.tips;
  var flavour = req.body.flavour;
  var avoid = req.body.avoid;
  var price = req.body.price;
  var isRecommended = req.body.isRecommended;

  var connection = getConnection();
  var updateSql = 'UPDATE dish SET introduction = ?, major_material = ?, minor_material = ?, method = ?, time = ?, process = ?, ' +
      'tips = ?, flavour = ?, avoid = ?, price = ?, isRecommend = ? WHERE id = ?';
  var updateSql_Params = [introduction, major, minor, method, time, procedure, tips, flavour, avoid, price, isRecommended, id];
  connection.query(updateSql, updateSql_Params, function (err, result){
    if(err){
      throw err;
      res.send('修改失败！');
      connection.end();
      return ;
    }

    connection.end();
    res.send('修改成功！');

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
