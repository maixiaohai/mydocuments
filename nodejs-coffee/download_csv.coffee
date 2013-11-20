# alllw the browser to create a CSV file and download it.
# _arrays = [[..], [..],.., [..]]
# ps: "\uFEFF" to solve the problem of wrong display of chinese in excel

module.exports =
  csv: (_filename, _arrays)->
    # store data into a string
    _str = ''
    for _arr in _arrays
      _line = ''
      for _index in _arr
        _line += ',' if _line isnt ''
        _line += _index
      _str += _line + '\r\n'

    # create the click to download csv file
    _a = $('<a class="hide">点击下载</a>')
    _a.attr "download", "#{_filename}.csv"
    blob = new Blob ["\uFEFF" + _str], {'type':'application\/octet-stream'}
    _a.attr "href", window.URL.createObjectURL(blob)
    $("body").append _a
    _a[0].click()
    setTimeout ->
      _a.remove()
    , 1000
