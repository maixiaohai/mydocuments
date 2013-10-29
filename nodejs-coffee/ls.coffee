# functions like ls command
# result <filenames of ./> <end> <filenames of ./>

fs = require 'fs'

filenames = fs.readdirSync '.'
for filename in filenames
  console.log filename

fs.readdir '.', (_err, filenames)->
  for filename in filenames
    console.log filename

console.log 'end'