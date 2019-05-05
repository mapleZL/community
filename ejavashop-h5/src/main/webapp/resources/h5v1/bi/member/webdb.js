/**  
 *数据库操作辅助类,定义对象、数据操作方法都在这里定义
 */
var dbname = 'websql'; /*数据库名*/
var version = '1.0'; /*数据库版本*/
var dbdesc = 'websqltest'; /*数据库描述*/
var dbsize = 2 * 1024 * 1024; /*数据库大小*/
var dataBase = null; /*暂存数据库对象*/
/*数据库中的表单名*/
var websqlTable = "websqlTable";

/**  
 * 打开数据库
 * @returns  dataBase:打开成功   null:打开失败
 */

function websqlOpenDB() {
	try { /*数据库有就打开 没有就创建*/
		dataBase = window.openDatabase(dbname, version, dbdesc, dbsize, function() {});
		if (dataBase) {
			//        alert("数据库创建/打开成功!");  
		} else {
			//        alert("数据库创建/打开失败！");  
		}
	} catch (e) {}
	return dataBase;
}
/**  
 * 新建数据库里面的表单
 * @param tableName:表单名
 */

function websqlCreatTable(tableName) {
	//  chinaAreaOpenDB();  
	try {
		var creatTableSQL = 'CREATE TABLE IF  NOT EXISTS ' + tableName + ' (rowid INTEGER PRIMARY KEY AUTOINCREMENT, MEMBER_NAME text,MEMBER_PASSWORD text)';
		dataBase.transaction(function(ctx, result) {
			ctx.executeSql(creatTableSQL, [], function(ctx, result) {
				//            alert("表创建成功 " + tableName);  
			}, function(tx, error) {
				//            alert('创建表失败:' + tableName + error.message);  
			});
		});
	} catch (e) {}
}
/**  
 * 往表单里面插入数据
 * @param tableName:表单名
 * @param NAME:姓名
 * @param AGE:年龄
 * @param HEIGHT:身高
 * @param WEIGTH:体重
 */

function websqlInsterDataToTable(tableName, MEMBER_NAME, MEMBER_PASSWORD) {
	try {
		var insterTableSQL = 'INSERT INTO ' + tableName + ' (MEMBER_NAME,MEMBER_PASSWORD) VALUES (?,?)';
		dataBase.transaction(function(ctx) {
			ctx.executeSql(insterTableSQL, [MEMBER_NAME, MEMBER_PASSWORD], function(ctx, result) {
				//            console.log("插入" + tableName  + MEMBER_NAME + "成功");  
			}, function(tx, error) {
				//            alert('插入失败: ' + error.message);  
			});
		});
	} catch (e) {}
}
/**  
 * 获取数据库一个表单里面的所有数据
 * @param tableName:表单名
 * 返回数据集合
 */

function websqlGetAllData(tableName) {
	try {
		var selectALLSQL = 'SELECT * FROM ' + tableName;
		dataBase.transaction(function(ctx) {
			ctx.executeSql(selectALLSQL, [], function(ctx, result) {
				//            alert('查询成功: ' + tableName + result.rows.length);  
				var len = result.rows.length;
				//表中只会存在一条关于用户信息的数据
				if (len == 1) {
					$("#userName").val(result.rows.item(0).MEMBER_NAME);
					$("#password").val(result.rows.item(0).MEMBER_PASSWORD);
				}
/*for(var i = 0;i < len;i++) {  
	                console.log("NAME = "  + result.rows.item(i).MEMBER_NAME);  
	                console.log("AGE = "  + result.rows.item(i).MEMBER_PASSWORD);  
	                console.log("-------- 我是分割线 -------");  
	            }  */
			}, function(tx, error) {
				//          alert('查询失败: ' + error.message);  
				//发生错误即没有查到表结构
				websqlCreatTable("MEMBER");
			});
		});
	} catch (e) {}
}
/**  
 * 获取数据库一个表单里面的部分数据
 * @param tableName:表单名
 * @param name:姓名
 */

function websqlGetAData(tableName, name, password) {
	websqlDeleteAllDataFromTable("MEMBER");
	websqlInsterDataToTable("MEMBER", name, password);
}
/**  
 * 删除表单里的全部数据
 * @param tableName:表单名
 */

function websqlDeleteAllDataFromTable(tableName) {
	try {
		var deleteTableSQL = 'DELETE FROM ' + tableName;
		localStorage.removeItem(tableName);
		dataBase.transaction(function(ctx, result) {
			ctx.executeSql(deleteTableSQL, [], function(ctx, result) {
				//            alert("删除表成功 " + tableName);  
			}, function(tx, error) {
				//            alert('删除表失败:' + tableName + error.message);  
			});
		});
	} catch (e) {}
}
/**  
 * 根据name删除数据
 * @param tableName:表单名
 * @param name:数据的姓名
 */

function websqlDeleteADataFromTable(tableName, name) {
	try {
		var deleteDataSQL = 'DELETE FROM ' + tableName + ' WHERE NAME = ?';
		localStorage.removeItem(tableName);
		dataBase.transaction(function(ctx, result) {
			ctx.executeSql(deleteDataSQL, [name], function(ctx, result) {
				//            alert("删除成功 " + tableName + name);  
			}, function(tx, error) {
				//            alert('删除失败:' + tableName  + name + error.message);  
			});
		});
	} catch (e) {}
}
/**  
 * 根据name修改数据
 * @param tableName:表单名
 * @param name:姓名
 * @param age:年龄
 */

function websqlUpdateAData(tableName, MEMBER_NAME, MEMBER_PASSWORD) {
	try {
		var updateDataSQL = 'UPDATE ' + tableName + ' SET MEMBER_PASSWORD = ? WHERE MEMBER_NAME = ?';
		dataBase.transaction(function(ctx, result) {
			ctx.executeSql(updateDataSQL, [MEMBER_NAME, MEMBER_PASSWORD], function(ctx, result) {
				//            alert("更新成功 " + tableName + MEMBER_NAME);  
			}, function(tx, error) {
				//            alert('更新失败:' + tableName  + MEMBER_NAME + error.message);  
			});
		});
	} catch (e) {}
}