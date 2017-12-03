# util.coffee, libqqp/tool/editor/lib/

config = require './config'


get_cursor_element = (editor, cursor) ->
  measure_map = editor.component.decorationsToMeasure.cursors
  # get cursor index
  sp = []
  measure_map.forEach (i) ->
    sp.push i.screenPosition
  cursor_i = 0
  for i in sp
    if i.isEqual cursor.getScreenPosition()
      break
    cursor_i += 1
  element_parent = editor.component.refs.cursorsAndInput.refs.cursors
  element_parent.children[cursor_i + 1]

_DELAY_MS = config.SHOW_SELECTION_TOOLTIP_DELAY_MS
# callback(editor, selection) -> tooltip?
selection_tooltip_helper = (callback) ->
  _on_editor = (editor) ->
    _on_selection = (selection) ->
      _init = ->
        scroll_top = null
        scroll_left = null
        resize_observer = null
        editor_element = editor.component.element

        tooltip = null
        _refresh_tooltip = ->
          _on_timeout = ->
            tooltip?.dispose()
            tooltip = callback editor, selection
          setTimeout _on_timeout, _DELAY_MS

        selection.onDidChangeRange (event) ->
          _refresh_tooltip()
        selection.onDidDestroy ->
          tooltip?.dispose()
          # clear scroll
          scroll_top?.dispose()
          scroll_left?.dispose()
          # clear resize
          resize_observer?.unobserve editor_element
        # support refresh on scroll
        scroll_left = editor.onDidChangeScrollLeft ->
          _refresh_tooltip()
        scroll_top = editor.onDidChangeScrollTop ->
          _refresh_tooltip()
        # support resize
        resize_observer = new ResizeObserver ->
          # check visible
          if (editor.component?) and editor.component.isVisible()
            _refresh_tooltip()
          else
            tooltip?.dispose()
        resize_observer.observe editor_element
        # init show tooltip
        _refresh_tooltip()
      setTimeout _init, _DELAY_MS
    editor.observeSelections _on_selection
  atom.workspace.observeTextEditors _on_editor


# callback(text) -> result
selection_process = (callback) ->
  s = atom.workspace.getActiveTextEditor()?.getSelections()
  if ! s?
    return
  # process each selection
  for i in s
    if i.isEmpty()
      continue
    raw = i.getText()
    result = callback raw
    # append result
    i.insertText "#{raw}#{result}"

selection_process_one_line = (callback) ->
  selection_process (text) ->
    " [ #{callback text} ] "


module.exports = {
  get_cursor_element
  selection_tooltip_helper

  selection_process
  selection_process_one_line
}
