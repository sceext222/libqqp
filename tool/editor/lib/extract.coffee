# extract.coffee, libqqp/tool/editor/lib/


# extract hex bytes from adb logcat (android_hook tools)
extract_logcat = (raw) ->
  _LINE_PREFIX_MARK = ' : '
  # process lines, remove prefix if any
  l = []
  for t in raw.split('\n')
    i = t.indexOf _LINE_PREFIX_MARK
    if i != -1
      t = t.slice(i + _LINE_PREFIX_MARK.length)
    l.push t
  text = l.join('')
  # check for '[' and ']'
  i = text.indexOf '['
  if i != -1
    text = text.slice(i + 1)
  i = text.indexOf ']'
  if i != -1
    text = text.slice(0, i)
  text

# extract hex bytes from wireshark text
extract_wireshark = (raw) ->
  "TODO"


module.exports = {
  extract_logcat
  extract_wireshark
}
