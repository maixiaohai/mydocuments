# hello world example of nodejs
# written in coffee
# iced -c helloworld.coffee will compile a js file of the same name
http = require 'http'

app = http.createServer (_req, _res)->
  _res.writeHead 200, {'Content-Type': 'text/html'}
  _res.write '<h1>Nodejs</h1>'
  _res.end '<p>Hello World</p>'
.listen(8888)

console.log "server at 127.0.0.1:8888"
