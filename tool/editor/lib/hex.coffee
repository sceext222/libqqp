# hex.coffee, libqqp/tool/editor/lib/

config = require './config'  # TODO


_is_hex_char = (c) ->
  if ((c >= '0') and (c <= '9')) or ((c >= 'a') and (c <= 'f')) or ((c >= 'A') and (c <= 'F'))
    return true
  false

hex_count_bytes = (text) ->
  count = 0
  for c in text
    if _is_hex_char c
      count += 1
  count / 2

get_hex = (text) ->
  o = ''
  for c in text
    if _is_hex_char c
      o += c
  o

hex_to_text = (raw) ->
  hex = get_hex raw
  b = Buffer.from hex, 'hex'
  b.toString 'utf-8'  # TODO

hex_to_10 = (raw) ->
  hex = get_hex raw
  Number.parseInt hex, 16

hex_to_ipv4 = (raw) ->
  hex = get_hex(raw)[...8]  # 4 Bytes for ipv4
  o = []
  for i in [0...4]
    one = Number.parseInt hex[i * 2 .. i * 2 + 1], 16
    o.push one
  o.join '.'

hex_format = (raw) ->
  hex = get_hex raw
  o = []
  # reset one line
  one = ''
  one_count = 0
  # flag: first char of one byte
  first = true
  for c in hex
    if first
      one += ' ' + c
    else
      one += c
      one_count += 1
      if one_count >= config.FORMAT_HEX_LINE_LENGTH
        o.push one
        # reset one line
        one = ''
        one_count = 0
    first = ! first
  # add last line
  if one.length > 0
    o.push one
  o.join '\n'


module.exports = {
  hex_count_bytes
  get_hex

  hex_to_text
  hex_to_10
  hex_to_ipv4

  hex_format
}
