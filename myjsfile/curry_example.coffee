# 一个定时任务，需要加入try-catch语句
# 将cronJob的第二个参数（一个函数）修改以方便未来定时任务的添加

cronJob = require('cron').CronJob
moment = require 'moment'

runFun = (_cb)->
  ->
    try
      _cb()
    catch _e
      console.log _e

new cronJob '00 */1 * * * *', runFun(->
  console.log 'helloworld'
  _now = moment().format 'YYYY-MM-DD, h:mm:ss'
  console.log "begin at #{_now}"
), (_err)->
  console.log _err
,
yes
