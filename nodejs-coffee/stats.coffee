# Don't try to do this with 'while' and 'break'
# This's a example of asynchronous function nested asynchronous function

http = require "http"
_ = require "underscore"

# 获取某url的数据
getdata = (_url, _callback) ->
  http.get _url, (_res)->
    _body = ""
    _res.on 'data', (_chunk) ->
      _body += _chunk
    _res.on 'end', ->
      try
        _stat = JSON.parse _body
      catch _e
        process.stderr.write _e
        process.exit 1
      if not _callback
        console.log _stat
      else
        _callback _stat
  " "

# 获得多个url的数据
request = (_urls, _ret, _callback = null)->
  _url = _urls.shift()
  getdata _url[1], (_data)->
    _ret[_url[0]] = _data
    _callback = console.log if not _callback
    return _callback _ret if _urls.length is 0
    request _urls, _ret, _callback

module.exports = {getdata, request}
