#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <string>
#include "../include/Server.h"
#include "../include/WebController.h"
#include "../include/JsonController.h"
#include "../include/JsonResponse.h"

using namespace std;
using namespace Mongoose;

class MyController : public WebController
{
    public:
        void hello(Request &request, StreamResponse &response)
        {
          //  response << "Hello " << htmlEntities(request.get("username", "... what's your name ?")) << endl;
            
            string username = request.get("username");
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
            cout << "data = " << data << endl;


           /* string body = request.get("body","no body contaent");
            cout << "body = " << body << endl;
            response << body << endl;*/
        }

        void setup()
        {
           // addRoute("GET", "/hello", MyController, hello);
            addRoute("POST","/hello",MyController,hello);
        }
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
