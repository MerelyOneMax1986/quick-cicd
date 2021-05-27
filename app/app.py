from flask import Flask
from flask_restful import Api, Resource, reqparse
import consul

app = Flask(__name__)
api = Api(app)


consul_client = consul.Consul(
    host='cserver',
    #host='172.19.0.2',
    port=8500,
)

print(consul_client)

# GET
class Consul(Resource):
    def get(self, key):
        value = ''
        try:
            # Set DEBUG flag using Consul KV store
            index, data = consul_client.kv.get(key)
            if data:
                value = data.get('Value') 
                return value.decode("utf-8"), 200
            else:
                return "Oops!  That was no valid key.  Try again...", 404    
        except ValueError:
            return "Oops!  That was no valid key.  Try again...", 404


# POST
class ConsulList(Resource):
    def post(self):    
        parser = reqparse.RequestParser()
        parser.add_argument('key', type=str, help='Consul key')
        parser.add_argument('value', type=str, help='Consul value')
        args = parser.parse_args()
        print(args['key'])
        print(args['value'])
        consul_client.kv.put(args['key'], args['value'])
        return "Success", 200

##
## Actually setup the Api resource routing here
##
api.add_resource(Consul, '/get_value/<string:key>')
api.add_resource(ConsulList, '/set_value')

if __name__ == '__main__':
    app.run(debug=True)     
