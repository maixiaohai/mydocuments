# difference between Async and Sync
# result will be :
# end. file2content end sync. file1content
fs = require 'fs'

fs.readFile 'file1.txt', 'utf-8', (_err, _data)->
  if _err
    console.log _err
  else
    console.log _data

console.log "end."

_data = fs.readFileSync 'file2.txt', 'utf-8'
console.log _data
console.log "end sync."