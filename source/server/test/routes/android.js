/**
 * Created by jh on 2016/7/17.
 */

var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
router.use(bodyParser.json());
router.use(bodyParser.urlencoded({ extended: false }));

/* GET users listing. */
router.get('/register',function(req,res){
    // register(req, res);                    // 注册
    console.log('get register');
});

router.post('/register',function(req,res){
    register(req, res);                    // 注册
});

router.post('/register',function(req,res){
    login(req, res);                       // 登录
});

router.post('/nearby',function(req,res){
    getNearbyRestaurant(req, res);         // 查找附近餐馆
});

router.post('/getFriendList',function(req,res){
    getFriendList(req, res);         // 查找好友餐馆
});

router.post('/getFriendInfo',function(req,res){
    getFriendInfo(req, res);         // 查找好友信息
});

router.post('/editInfo',function(req,res){
    editInfo(req, res)              // 修改个人信息
});


function getNearbyRestaurant(req, res){
    var city = req.body.city;
    var province = req.body.province;
    var area = req.body.area;

    var pool = getConnection();

    var resQuerySql = 'SELECT * FROM restaurant city  = ?';
    var resQuerySql_Params = [city];

    pool.query(resQuerySql, resQuerySql_Params,function (error, rows, fields) {
        if(error){
            throw error;
            res.send('查找餐馆失败！');
            pool.end();
            return false;
        }

        pool.end();
        res.send({rows: rows})
    });
}

function register(req, res){

    var name = req.body.username;
    var pwd = req.body.password;
    var email = req.body.email;
    var phone = req.body.phone;
    var city = req.body.city;
    var area = req.body.area;
    var province = req.body.province;
    var pic = req.body.pic;
    var label = req.body.name;

    // 查看是否有相同用户名
    var check = 'SELECT id FROM user_all WHERE username = ?';
    var check_Params = [name];

    var pool = getConnection();
    pool.query(check, check_Params, function(err, rows, result){

        if(err){
            throw err;
            pool.end();
            res.send('注册失败！');
            return false;
        }else if(rows.length == 0){
            res.send('已存在该用户名！');
            pool.end();
            return false;
        }

        // 用户插入身份总表
        var id;
        var userAddSql = 'INSERT INTO user_all(username, password, identity) VALUES(?, ?, 1)';  // 用户identity为1
        var userAddSql_Params = [name, pwd];
        pool.query(userAddSql, userAddSql_Params, function(err, result){

            if(err){
                throw err;
                pool.end();
                res.send('注册失败！');
                return false;
            }

            id = result.insertId;

            // 用户喜好插入至label表
            var label_id;
            var labelAddSql = 'INSERT INTO label(id, ??, identity) VALUES(?, 1, 1)';  // 用户identity为1
            var labelAddSql_Params = [id, label];

            pool.query(labelAddSql, labelAddSql_Params, function(err, result){
                if(err){
                    throw err;
                    pool.end();
                    res.send('注册失败！');
                    return false;
                }

                label_id = result.insertId;

                // 插入至用户表
                var userAllSql = 'INSERT INTO user(id , phone, email, pic , label) VALUES(?, ?, ?, ?, ?)';
                var userAllSql_Params = [id, phone, email, pic, label_id];
                pool.query(userAllSql, userAllSql_Params, function(err, result){
                    if(err){
                        throw err;
                        pool.end();
                        res.send('注册失败！');
                        return false;
                    }

                    pool.end();
                    res.send('注册成功！');
                });
            });
        });
    });
}

function login(req, res) {
    // 从此路径检测到post方式则进行post数据的处理操作
    //get User info
    //这里的User就是从model中获取user对象，通过global.dbHandel全局方法（这个方法在app.js中已经实现)
    var uname = req.body.username;
    var upwd = req.body.password;//获取post上来的 data数据中 uname的值

    var pool = getConnection();
    pool.query('use sinfo');

    function strlen(str){
        var len = 0;
        for (var i=0; i<str.length; i++) {
            var c = str.charCodeAt(i);
            //单字节加1
            if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) {
                len++;
            }
            else {
                len+=2;
            }
        }
        return len;
    }

    var length=strlen(uname) ;
    if(length<4) {
        var check = 'SELECT * FROM ?? where username = ? and password = ? and identity = ?';
        var post =['user_all',uname,upwd,3];
        pool.query(check, post, function (err, result) {
            if (err) {
                throw err;
                pool.end();
                res.send('fail');
                return false;
            }
            if (result[0] != null) {
                console.log('登陆成功！');
                console.log(result[0]);
                pool.end();
                return result;
                res.send('sucess');
            }
            else {
                console.log('登录失败！');
                pool.end();
                res.send('error');
            }
        });
    }
    else if(length>15)
    {
        pool.end();
        res.send('用户名格式不正确！');

    }
    else{
        var check = 'SELECT * FROM ?? where username = ? and password = ? and identity = ?';
        var post =['user_all',uname,upwd,2];
        pool.query(check, post, function (err, result) {

            if (err) {
                throw err;
                pool.end();
                res.send('fail');
            }
            else if (result[0] != null) {
                console.log('登陆成功！');
                console.log(result[0]);
                pool.end();
                res.send('sucess');
                return result;
            }
            else {
                pool.end();
                res.send('error');
                console.log('登录失败！');
            }
        });
    }
}

function getFriendList(req, res)
{
    var id = req.body.id;
    var check = 'SELECT * FROM user WHERE id IN (SELECT friend_id FROM friend WHERE user_id = ?)';
    var post =[id];

    var pool = getConnection();
    pool.query(check, post, function (err, result) {
        if (err) {
            throw err;
            pool.end();
            res.send('fail');
        }

        pool.end();
        res.send({result : result}) ;
    });
}

function getFriendInfo(req, res)
{
    var id = req.body.id;

    var check = 'SELECT * FROM user where id = ?';
    var post=[id];

    var pool = getConnection();
    pool.query(check, post, function (err, rows, field) {
        if (err) {
            throw err;
            pool.end();
            res.send('fail');
            return false;
        }
        // console.log(result);
        pool.end();
        res.send({rows : rows}) ;
    });
}


function editInfo(req, res)
{

    var id = req.body.id;
    var email = req.body.email;
    var phone = req.body.phone;

    var check = 'update user set email=?, phone=? where id = ?';
    var post=[email, phone, id];

    var pool = getConnection();
    pool.query(check, post, function (err, result) {
        if (err) {
            throw err;
            pool.end();
            res.send('fail');
            return false;
        }

        pool.end();
        console.log('上传成功');
        res.send('success') ;
    });
}

function getInfo(req, res)
{

    var id = req.body.id;

    var check = 'SELECT * FROM user WHERE id IN (SELECT id FROM user_all WHERE username = ? AND identity = ?)';
    var post=[id, 1];

    var pool = getConnection();
    pool.query(check, post, function (err, result) {
        if (err) {
            throw err;
            pool.end();
            res.send('fail');
            return false;
        }

        pool.end();
        res.send({result : result}) ;
    });
}

function getRestaurantMenu(req, res)
{

    var id = req.body.id;

    var querySql = 'SELECT * FROM dish WHERE id IN (SELECT dish_id FROM menu WHERE restaurant_id = ?)';
    var querySql_Params = [id];

    var connection = getConnection();
    connection.query(querySql, querySql_Params, function(error, results, field){
        if(error){
            throw error;
            connection.end();
            res.send('fail');
            return ;
        }

        res.send({results : results});
    });
}

// 查找用户
function searchUser(req, res){
    var name = req.body.name;

    var query = 'SELECT * FROM user WHERE id IN (SELECT id FROM user_all WHERE username = ? )';
    var query_Params = [name];

    var connection = getConnection();
    connection.query(query, query_Params, function(error, results, field){

        if(error){
            throw error;
            connection.end();
            res.send('查找失败！');
            return ;
        }else if(results.lenght == 0){
            connection.end();
            res.send('不存在该用户！');
            return ;
        }else{
            connection.end();
            res.send({results : results});
        }
    });
}

// 添加好友
function addFriend(req, res){


    var name = req.body.name;
    var friend_name = req.body.friend;

    var querySql = 'SELECT id FROM user_all WHERE username = ?';
    var querySql_Params = [name];
    var id, friend_id;

    var connection = getConnection();
    connection.query(querySql, querySql_Params, function(error, results, field){

        if(error){
            throw error;
            connection.end();
            res.send('添加失败！');
            return ;
        }

        id = results[0].id;
        var querySql_Params1 = [friend_name];


        connection.query(querySql, querySql_Params1, function(error, results, field){
            if(error){
                throw error;
                connection.end();
                res.send('添加失败！');
                return ;
            }

            friend_id = results[0].id;

            var insertSql = 'INSERT INTO friend VALUES(?, ?)'; // user_id  friend_id
            var insertSql_Params = [id, friend_id];
            connection.query(insertSql, insertSql_Params, function(error, results){
                if(error){
                    throw error;
                    connection.end();
                    res.send('添加失败！');
                    return ;
                }

                connection.end();
                res.send('添加成功！');

            });
        });
    });

}


// 发布食友圈
function publish(req, res){

    var username = req.body.username;
    var content = req.body.content;
    var time = new Date();

    var addSql = 'INSERT INTO moments(user_id, time, content)';
    var addSql_Params = [username, time, content];

    var connection = getConnection();
    connection.query(addSql, addSql_Params, function(error, results){
        if(error){
            throw error;
            connection.end();
            res.send('发布失败！');
            return ;
        }

        connection.end();
        res.send('发布成功！');
    });
}

// 查看食友圈
function moment(req, res){

    var username = req.body.username;

    var query = 'SELECT * FROM moments WHERE user_id IN (SELECT friend_id FROM friend WHERE user_id IN (SELECT id FROM user_all WHERE username = ?))';
    var query_Params = [username];

    var connection = getConnection();
    connection.query(query, query_Params, function(error, results){
        if(error){
            throw errow;
            connection.end();
            res.send('fail');
            return ;
        }

        connection.end();
        res.send({results : results});
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
