# index.coffee, libqqp/tool/editor/

{ CompositeDisposable } = require 'atom'


activate = (state) ->
  s = @subscriptions = new CompositeDisposable()

  # TODO
  hex_count_bytes = ->
    console.log "TODO: hex_count_bytes"
    # TODO

  hex_to_text = ->
    console.log "TODO: hex_to_text"
    # TODO

  hex_to_10 = ->
    console.log "TODO: hex_to_10"
    # TODO

  hex_to_ipv4 = ->
    console.log "TODO: hex_to_ipv4"
    # TODO

  hex_format = ->
    console.log "TODO: hex_format"
    # TODO

  extract_logcat = ->
    console.log "TODO: extract_logcat"
    # TODO

  extract_wireshark = ->
    console.log "TODO: extract_wireshark"
    # TODO

  s.add atom.commands.add('atom-text-editor', {
    'libqqp:hex_count_bytes': hex_count_bytes
    'libqqp:hex_to_text': hex_to_text
    'libqqp:hex_to_10': hex_to_10
    'libqqp:hex_to_ipv4': hex_to_ipv4

    'libqqp:hex_format': hex_format

    'libqqp:extract_logcat': extract_logcat
    'libqqp:extract_wireshark': extract_wireshark
  })


deactivate = ->
  @subscriptions.dispose()

serialize = ->
  {}  # TODO

module.exports = {
  subscriptions: null

  activate
  deactivate
  serialize
}
