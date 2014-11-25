#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <string>
#include "../include/Server.h"
#include "../include/WebController.h"
#include "../include/JsonController.h"
#include "../include/JsonResponse.h"
#include "../include/json.h"
#include "../include/mysql++.h"
#include "../include/StreamResponse.h"
#include "../include/options.h"
#include "../include/row.h"


using namespace std;
using namespace Mongoose;
using namespace mysqlpp;
using namespace Json;


class UserController : public WebController
{
    public:
        void reg(Request &request,StreamResponse &response) {
            string body = request.getData();

            Json::Reader reader;
          //  Json::Writer writer;
            Json::Value value;
            Json::Value root;
            Json::Value code;
            Json::Value data(objectValue);
            Json::Value dataObj(objectValue);

            if (reader.parse(body,value)) {
                string username = value["username"].asString();
                string passwd = value["passwd"].asString();

                cout << "注册用户名：　" << username << endl;
                cout << "注册密码: " << passwd << endl;

                //mysqlpp::Connection conn = openConnection("httpclient","127.0.0.1","root","root");
                
                Connection conn = Connection(false);
                conn.set_option(new SetCharsetNameOption("utf8"));
                conn.connect("httpclient","127.0.0.1","root","root");
                // conn.set_option(new SetCharsetNameOption("utf8"));
                mysqlpp::Query query = conn.query();
                
                /*string sql1 = ToUTF8(L("select * from t_user where username='" + username + "'"));
                string sql2 = ToUTF8(L("insert into t_user(username,passwd) values('" + username + "','" + passwd + "')"));  */

                string sql1 = "select * from t_user where username='" + username + "'";
                string sql2 = "insert into t_user(username,passwd) values('" + username + "','" + passwd + "')";  
              //  print_stock_table(query);

             //   mysqlpp::Transaction trans(conn,mysqlpp::Transaction::serializable,mysqlpp::Transaction::session);

              //  mysqlpp::SimpleResult result = query.execute(sql1);
              //  mysqlpp::ulonglong r = result.rows();
                query << sql1 ;
                mysqlpp::StoreQueryResult result = query.store();
                mysqlpp::StoreQueryResult::list_type::size_type r = result.num_rows();


                cout << "sql1 = " << sql1 << endl;
                cout << "sql2 = " << sql2 << endl;
                cout << "r = " << r << endl;

                if (r > 0) {
                    //用户已存在
                    root["code"] = "1";
                } else {
                    SimpleResult sr = query.execute(sql2);
                    int row = sr.rows();

                    if (row > 0) {
                        root["code"] = 0;
                        ulonglong userid = query.insert_id();

                        dataObj["userid"] = userid;
                        cout << "userid = " << userid << endl;
                        root["data"] = dataObj;

                    } else {
                        //数据库调用异常
                        root["code"] = 4;
                    }
                }

       /*         if (r > 0) {
                    response << "1" << endl;
                } else {
                    if(query.execute(sql2).rows() > 0) {
                         response << "0" << endl;
                     } else {
                         response << "2" << endl;
                     }
                }
                cout << "query = " << query << endl;*/
                conn.disconnect();
            } else {
                //数据解析异常
                root["code"] = 2;
            }

            response << root.toStyledString();
        };

        void login(Request &request,StreamResponse &response) {
            string body = request.getData();
            cout << "body = " << body << endl;
            Json::Reader reader;
            Json::Value value;
            Json::Value code;
            Json::Value root(objectValue);
            Json::Value data(objectValue);
            Json::Value dataObj(objectValue);

            if (reader.parse(body,value)) {
                string username = value["username"].asString();
                string passwd = value["passwd"].asString();

                cout << "username = " << username << endl;
                cout << "passwd = " << passwd << endl;

                Connection conn = Connection(false);
                conn.set_option(new SetCharsetNameOption("utf8"));
                conn.connect("httpclient","127.0.0.1","root","root");

                string sql = "select id from t_user where username = '" + username + "' and passwd = '" + passwd + "'";
                cout << "sql = " << sql << endl;
                Query query = conn.query();
                query << sql;
                StoreQueryResult result = query.store();
                //mysqlpp::StoreQueryResult::list_type::size_type r = result.num_rows();
               // Row row = result.fetch_row();
               // Row::const_reference count = row.at(0);
                //cout << "count = " << count << endl;
               //string field_name = result.field_name(0);
               //cout << "field_name = " << field_name << endl;
              //  Row row = result.fetch_row();
            //    cout << row.begin() << endl;
                StoreQueryResult::const_iterator it = result.begin();
               // ++it;
                if (it != result.end()) {
                    Row row = *it;
                    root["code"] = 0;
                    dataObj["userid"] = row[0].c_str();
                    root["data"] = dataObj;

                    cout << row[0] << "<<<<<<<<<<<<<<<<<<" << endl;
                   // cout << (row[1]["id"] + "<<<<<<<<<<<<<<<<<<") << endl;
                    cout << root.toStyledString() << endl;
                } else {

                }



              /*  Row::list_type::size_type num_row = result.num_rows();
                cout << "num_row = " << num_row << endl;

                if (num_row) {
                    cout << ">0" << endl;
                } else {
                    cout << "<0" << endl;
                }*/

                //cout << *it << endl;
             //   cout << "it = " << *it << endl;
               // Row row = *it;

               /* if (!row.empty()) {
                    root["code"] = "0";
                    cout << "row[0] = " << row[0] << endl;
                    dataObj["userid"] = row[0] + "";
                    root["data"] = dataObj;
                } else {
                    root["code"] = "1";
                }*/

                /*if (it != result.end()) {
                    if (row[0]) {
                        root["code"] = "0";
                        //response << "0" << endl;
                    } else {
                        //response << "1" << endl;
                        root["code"] = "1";
                    }
                    //cout << "cout = " << row[0] << endl;
                }*/
              //  cout << "count = " << *it[0] << endl;
            } else {
                root["code"] = "2";
            }
            response << root.toStyledString();
        }

        void addMsg(Request &request,StreamResponse &response) {
            

            string body = request.getData();

            Json::Reader reader;
            Json::Value value;
            Json::Value code;
            Json::Value root(objectValue);
            Json::Value dataObj;

            if(reader.parse(body,value)) {
                string userid = value["userid"].asString();
                string title = value["title"].asString();
                string content = value["content"].asString();

                cout << "userid = " << userid << endl;
                cout << "title = " << title << endl;
                cout << "content = " << content << endl;

                Connection conn = Connection(false);
                conn.set_option(new SetCharsetNameOption("utf8"));
                conn.connect("httpclient","127.0.0.1","root","root");

                string sql1 = "insert into t_msg(title,content,user_id) values('" + title + "','"
                            + content + "'," + userid + ")";
                Query query = conn.query();
                SimpleResult sr = query.execute(sql1);

                cout << "sql = " << sql1 << endl;

                int row = sr.rows();
                if (row > 0) {
                    root["code"] = "0";
                } else {
                    root["code"] = "4";
                }
                conn.disconnect();
            } else {
                root["code"] = "2";
            }

            response << root.toStyledString();
        }

        void delMsg(Request &request,StreamResponse &response) {
            Connection conn = Connection(false);
            conn.set_option(new SetCharsetNameOption("utf8"));
            conn.connect("httpclient","127.0.0.1","root","root");

            string body = request.getData();

            Json::Reader reader;
            Json::Value value;
            Json::Value root(objectValue);
            Json::Value dataObj;

            if (reader.parse(body,value)) {
                string msgid = value["msgid"].asString();

                string sql = "delete from t_msg where id = " + msgid;
                
                cout << "delete sql == " << sql << endl;
                cout << "msg id = " << msgid << endl;

                Query query = conn.query();
                SimpleResult res = query.execute(sql);
                ulonglong rows = res.rows();
                cout << "rows = " << rows << endl;
                if (rows > 0) {
                    root["code"] = "0";
                } else {
                    root["code"] = "1";
                }
            } else {
                root["code"] = "2";
            }
            response << root.toStyledString();
            conn.disconnect();
        }

        void showDetail(Request &request,StreamResponse &response) {
            string body = request.getData();

            Json::Reader reader;
            Json::Value value;
            Json::Value root(objectValue);
            Json::Value dataObj(objectValue);
            if (reader.parse(body,value)) {
                string msgid = value["msgid"].asString();
                string sql = "select * from t_msg where id = " + msgid;

                cout << "sql = " << sql << endl;

                Connection conn = Connection(false);
                conn.set_option(new SetCharsetNameOption("utf8"));
                conn.connect("httpclient","127.0.0.1","root","root");

                Query query = conn.query();
                query << sql;
                StoreQueryResult result = query.store();
                StoreQueryResult::const_iterator it = result.begin();

                if (it != result.end()) {
                    Row row = *it;
                    string id  = row["id"].c_str();
                    string title = row["title"].c_str();
                    string content = row["content"].c_str();

                    root["code"] = "0";
                    dataObj["id"] = id;
                    dataObj["title"] = title;
                    dataObj["content"] = content;

                    root["data"] = dataObj;
                } else {
                    root["code"] = "1";
                }
                conn.disconnect();
            } else {
                root["code"] = "2";
            }
            response << root.toStyledString();
        }

        void updateMsg(Request &request,StreamResponse &response) {
            string body = request.getData();

            Json::Reader reader;
            Json::Value value;
            Json::Value root(objectValue);

            if (reader.parse(body,value)) {
                string msgid = value["msgid"].asString();
                string title = value["title"].asString();
                string content = value["content"].asString();

                string sql = "update t_msg set title = '"
                            + title + "' ,content  = '"
                            + content + "' where id = " 
                            + msgid;

                cout << "sql = " << sql << endl;

                Connection conn = Connection(false);
                conn.set_option(new SetCharsetNameOption("utf8"));
                conn.connect("httpclient","127.0.0.1","root","root");

                Query query = conn.query();
                SimpleResult result = query.execute(sql);

                cout << "result.rows()" << result.rows() << endl;

                if (result.rows() > 0) {
                    root["code"] = "0";
                } else {
                    root["code"] = "1";
                }
            } else {
                root["code"] = "2";
            }
            response << root.toStyledString();
        }

        void listMsg(Request &request,StreamResponse &response) {
            string body = request.getData();

            Json::Reader reader;
            Json::Value value;
            Json::Value root(objectValue);
            Json::Value dataObj;

            if (reader.parse(body,value)) {
                root["code"] = "0";

                string userid = value["userid"].asString();

                cout << "userid = " << userid << endl;

                string sql = "select * from t_msg where user_id = " + userid;

                Connection conn = Connection(false);
                conn.set_option(new SetCharsetNameOption("utf8"));
                conn.connect("httpclient","127.0.0.1","root","root");

                Query query = conn.query();
                query << sql;
                StoreQueryResult result = query.store();
                StoreQueryResult::const_iterator it ;

                cout << "num_row" << result.num_rows() << endl;

                if (result.num_rows() > 0) {
                    for (it = result.begin();it != result.end();++it) {
                        Row row = *it;
                        Json::Value item(objectValue);
                        item["id"] = row["id"].c_str();
                        item["title"] = row["title"].c_str();
                        item["content"] = row["content"].c_str();

                        cout << ">>>>>>>>>>>>>>>" << endl;

                        dataObj.append(item);
                    }
                    root["data"] = dataObj;
                }
                conn.disconnect();
            } else {
                root["code"] = "1";
            }

            response << root.toStyledString();
        }

        void setup()
        {
            addRoute("POST","/reg",UserController,reg);
            addRoute("POST","/login",UserController,login);
            addRoute("POST","/msg_add",UserController,addMsg);
            addRoute("POST","/msg_list",UserController,listMsg);
            addRoute("POST","/msg_delete",UserController,delMsg);
            addRoute("POST","/msg_detail",UserController,showDetail);
            addRoute("POST","/msg_update",UserController,updateMsg);
        }


      /*  Connection openConnection(char* database,char* server,char* user,char* passwd) {
        	Connection conn = Connection(false);
        	conn.set_option(new SetCharsetNameOption("utf8"));
        	conn.connect(database,server,user,passwd);
        	return conn;
        }*/
};


int main()
{
    UserController userController;
    Server server(8080);
    server.registerController(&userController);

    server.start();

    cout << "Server has started,listening port is 8080 " <<  endl;

    while (1) {
        sleep(10);
    }
}