# demo of download from api
fs = require('fs')
http = require('http')

server = http.createServer (req, res)->
  _filePath = '/Users/zhangxu/Downloads/(运营商)(2015-07-06-2015-07-12)(2b2a7c7).xlsx'
  _filename = "测试.xlsx"
  _exists = fs.existsSync _filePath
  if not _exists
    console.log "找不到相应文件"
    return
  else
    res.setHeader 'Content-Disposition', 'attachment; filename='+encodeURIComponent(_filename)
    _opt =
      bufferSize : 1024 * 1024
      encoding: 'binary'
    fReadStream = fs.createReadStream _filePath, _opt
    fReadStream.on 'data', (chunk)->
      res.write(chunk, 'binary')
    fReadStream.on 'end', ()->
      res.end()
# another way
# fReadStream.pipe(res)  
server.listen('8000')