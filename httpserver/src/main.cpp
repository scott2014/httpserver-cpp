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
//#include <tchar.h>

using namespace std;
using namespace Mongoose;
using namespace mysqlpp;

class MyController : public WebController
{
    public:
        void hello(Request &request, StreamResponse &response)
        {
          //  response << "Hello " << htmlEntities(request.get("username", "... what's your name ?")) << endl;
            
/*            string username = request.get("username");
            string passwd = request.get("passwd");
            string body = response.getBody();
            string data = request.getData();

            cout << "username = " << username << endl;
            cout << "passwd = " << passwd << endl;
            cout << "request method = " << request.getMethod() << endl;

            if (username == "yhj" && passwd == "123") {
               response << "{\"msg\":\"Congratulations,Login success\"}" << endl;
            } else {
              response << "{\"msg\":\"Sorry,wrong username or password\"}" << endl;
            }

            response << "body = " << body << endl;
            cout << "body = " << body << endl;
            response << "data = " << data << endl;
            cout << "data = " << data << endl;*/


           /* string body = request.get("body","no body contaent");
            cout << "body = " << body << endl;
            response << body << endl;*/


            string body = request.getData();

            Json::Reader reader;
            Json::Value value;
            if (reader.parse(body,value)) {
                string username = value["username"].asString();
                string passwd = value["passwd"].asString();

                cout << "Username = " << username << endl;
                cout << "Password = " << passwd << endl;

                if (username == "yhj" && passwd == "123") {
                    response << "Congratulations,Login OK" << endl;
                } else {
                    response << "Sorry,Login fail,wrong name or passwd!!!" << endl;
                }
            }

        };
        
        void reg(Request &request,StreamResponse &response) {
            string body = request.getData();

            Json::Reader reader;
            Json::Value value;

            if (reader.parse(body,value)) {
                string username = value["username"].asString();
                string passwd = value["passwd"].asString();

                cout << "注册用户名：　" << username << endl;
                cout << "注册密码: " << passwd << endl;

                mysqlpp::Connection conn = mysqlpp::Connection(false);
                conn.set_option(new SetCharsetNameOption("utf8"));
                conn.connect("cpp","127.0.0.1","root","root");
                
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
                    response << "1" << endl;
                } else {
                    if(query.execute(sql2).rows() > 0) {
                         response << "0" << endl;
                     } else {
                         response << "2" << endl;
                     }
                }
                cout << "query = " << query << endl;
            } else {
                response << "2" << endl;
            }
        };

        void setup()
        {
           // addRoute("GET", "/hello", MyController, hello);
            addRoute("POST","/hello",MyController,hello);
            addRoute("POST","/reg",MyController,reg);
        }


       /* std::string ToUTF8(const wchar_t* wideStr)
        {
               std::wstring_convert<std::codecvt_utf8<wchar_t>> conv;
               return conv.to_bytes(wideStr);
        }*/
};


int main()
{
    MyController myController;
    Server server(8080);
    server.registerController(&myController);

    server.start();

    cout << "Server has started,listening port is 8080 " <<  endl;

    while (1) {
        sleep(10);
    }
}
