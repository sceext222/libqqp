# index.coffee, libqqp/tool/editor/

{ CompositeDisposable } = require 'atom'

config = require './lib/config'
util = require './lib/util'
hex = require './lib/hex'
extract = require './lib/extract'


_update_tooltip_hex_count_bytes = (editor, selection) ->
  # check hex_count_bytes enable
  if ! module.exports.enable_hex_count_bytes
    return null

  if selection.isEmpty()
    return null
  bytes = hex.hex_count_bytes selection.getText()

  cursor = util.get_cursor_element editor, selection.cursor
  if ! cursor?
    return null
  atom.tooltips.add cursor, {
    title: "#{bytes} Bytes"
    trigger: 'manual'
  }

_format_hex = (text) ->
  " [\n#{hex.hex_format text}\n]"


activate = (state) ->
  s = @subscriptions = new CompositeDisposable()

  toggle_hex_count_bytes = ->
    module.exports.enable_hex_count_bytes = ! module.exports.enable_hex_count_bytes

  hex_to_text = ->
    util.selection_process_one_line hex.hex_to_text

  hex_to_10 = ->
    util.selection_process_one_line hex.hex_to_10

  hex_to_ipv4 = ->
    util.selection_process_one_line hex.hex_to_ipv4

  hex_format = ->
    util.selection_process _format_hex

  extract_logcat = ->
    util.selection_process (raw) ->
      _format_hex extract.extract_logcat(raw)

  extract_wireshark = ->
    util.selection_process (raw) ->
      _format_hex extract.extract_wireshark(raw)

  s.add atom.commands.add('atom-text-editor', {
    'libqqp:hex-count-bytes': toggle_hex_count_bytes
    'libqqp:hex-to-text': hex_to_text
    'libqqp:hex-to-10': hex_to_10
    'libqqp:hex-to-ipv4': hex_to_ipv4

    'libqqp:hex-format': hex_format

    'libqqp:extract-logcat': extract_logcat
    'libqqp:extract-wireshark': extract_wireshark
  })
  # hex_count_bytes
  s.add util.selection_tooltip_helper(_update_tooltip_hex_count_bytes)


deactivate = ->
  @subscriptions.dispose()

serialize = ->
  {}  # TODO

module.exports = {
  subscriptions: null
  enable_hex_count_bytes: false

  activate
  deactivate
  serialize
}
