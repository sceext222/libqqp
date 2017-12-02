FormatPack = {}

function FormatPack:new()
	local o = {}
	o.data = ''
    setmetatable(o, self)
	self.__index = self
	return o
end

function FormatPack:pushStr(str,col)
	if col == nil then
		self.data = self.data .. str .. '\n'
	else
		self.data = self.data .. FormatPack.getSpaceStr(col) .. str .. '\n'
	end
	return self
end

function FormatPack.getSpaceStr(col)
	local s = ''
	for i = 1, col,1 do
		s = s .. ' '
	end
	return s
end

function FormatPack:toData(data)
	return  FormatPack.formatHex(data) .. '\n[' ..'\n' .. self.data .. ']' .. '\n'
end

---格式化HEX字符串----
function FormatPack.formatHex(data)
	local times = string.len(data)
	local i = 1
	local str = ''
	while (i <  times) do
		str =  str .. string.sub(data,i,i + 1) .. ' '
		i = i + 2
	end
	return str
end

---
-- @function: 打印table的内容，递归
-- @param: tbl 要打印的table
-- @param: level 递归的层数，默认不用传值进来
-- @param: filteDefault 是否过滤打印构造函数，默认为是
-- @return: return
function FormatPack.printTable( tbl , level, filteDefault)
  local msg = ""
  filteDefault = filteDefault or true --默认过滤关键字（DeleteMe, _class_type）
  level = level or 1
  local indent_str = ""
  for i = 1, level do
    indent_str = indent_str.."  "
  end

  lua_log(indent_str .. "{")
  for k,v in pairs(tbl) do
    if filteDefault then
      if k ~= "_class_type" and k ~= "DeleteMe" then
        local item_str = string.format("%s%s = %s", indent_str .. " ",tostring(k), tostring(v))
        lua_log(item_str)
        if type(v) == "table" then
          PrintTable(v, level + 1)
        end
      end
    else
      local item_str = string.format("%s%s = %s", indent_str .. " ",tostring(k), tostring(v))
      lua_log(item_str)
      if type(v) == "table" then
        PrintTable(v, level + 1)
      end
    end
  end
  lua_log(indent_str .. "}")
end

return FormatPack
