AndroidQQ = {}

function AndroidQQ:new(data)
	local o = {}
	o.isSend = false
	o.size = 0
	o.type = 0
	o.encodeType = 0
	o.qq = 0

	o.data = string.sub(data,1)
    setmetatable(o, self)
	self.__index = self
	return o
end

function AndroidQQ:packHandler(isSend)
	local hex = HexHelper:new(self.data)
	local h_len = lua_hex_to_int(hex:getData(4))
	if h_len  > hex.size then
		return ''
	end
	return '1'
end

--准备格式化
function AndroidQQ:myFormat(isSend)
	self.isSend = isSend
	if isSend then
		return self:sendFormat(self.data)
	end
	return self:recvFormat(self.data)
end

--发送包格式化
function AndroidQQ:sendFormat(data)
	local hex = HexHelper:new(data)
	--缩进
	local col = 0
	local str = ''
	--长度4
	local h_size = hex:getDataA(4)

	--lua_log('kong' .. self.size)

	lua_log(h_size .. '5555555')
	--self.size = lua_hex_to_int(h_size)
	lua_log(h_size .. 'xx')
	--h_size = FormatPack.formatHex(h_size) .. '  --长度:' .. self.size
	-- str = str .. FormatPack.getSpaceStr(col) .. h_size .. '\n'
	-- --包类型
	-- local h_type = hex:getDataA(4)
	-- self.type = hex:readInt()
	-- h_type = FormatPack.formatHex(h_type) .. '  --类型:' .. self.type
	-- str = str .. FormatPack.getSpaceStr(col) .. h_type .. '\n'
	-- --包加密方式
	-- local h_encodeType = hex:getDataA(1)
	-- self.encodeType = hex:readByte() .. ''
	-- h_encodeType = FormatPack.formatHex(h_encodeType) .. '  --加密方式:' .. self.encodeType
	-- str = str .. FormatPack.getSpaceStr(col) .. h_encodeType .. '\n'
	-- --
	-- local h_n = hex:getData(4)
	-- h_n = FormatPack.formatHex(h_n)
	-- str = str .. FormatPack.getSpaceStr(col) .. h_n .. '\n'
	-- --
	-- local h_nn = hex:getData(1)
	-- h_nn = FormatPack.formatHex(h_nn)
	-- str = str .. FormatPack.getSpaceStr(col) .. h_nn .. '\n'
	-- --QQ长度
	-- local h_qq_len = hex:getDataA(4)
	-- local qq_len = hex:readInt() - 4
	-- h_qq_len = FormatPack.formatHex(h_qq_len) .. '  --QQ长度:' .. qq_len
	-- str = str .. FormatPack.getSpaceStr(col) .. h_qq_len .. '\n'
	-- --QQ号码
	-- local h_qq = hex:getData(qq_len)
	-- self.qq = lua_hex_to_asscii(h_qq)
	-- h_qq = FormatPack.formatHex(h_qq) .. '  --QQ:' .. self.qq
	-- str = str .. FormatPack.getSpaceStr(col) .. h_qq .. '\n'
	-- --包头包体
	-- local h_body = hex:getData(self.size - qq_len - 4 - 1 - 4 -1 - 4)
	-- -- local h_body_str = FormatPack.formatHex(h_body)
	-- -- self.fPack:pushStr(h_body_str,col)

	-- str = str .. FormatPack.getSpaceStr(col) .. self:headBodyFormat(col + 1,h_body) .. '\n'

	-- str = FormatPack.formatHex(data) .. '\n[' ..'\n' .. str .. ']\n'
	return str
end

--接收包格式化
function AndroidQQ:recvFormat(data)
	local hex = HexHelper:new(data)
	--缩进
	local col = 0
	local str = ''
	--长度4
	local h_size = hex:getDataA(4)
	self.size = hex:readInt()
	h_size = FormatPack.formatHex(h_size) .. '  --长度:' .. self.size
	str = str .. FormatPack.getSpaceStr(col) .. h_size .. '\n'
	--包类型
	local h_type = hex:getDataA(4)
	self.type = hex:readInt()
	h_type = FormatPack.formatHex(h_type) .. '  --类型:' .. self.type
	str = str .. FormatPack.getSpaceStr(col) .. h_type .. '\n'
	--包加密方式
	local h_encodeType = hex:getDataA(1)
	self.encodeType = hex:readByte() .. ''
	h_encodeType = FormatPack.formatHex(h_encodeType) .. '  --加密方式:' .. self.encodeType
	str = str .. FormatPack.getSpaceStr(col) .. h_encodeType .. '\n'
	--
	local h_nn = hex:getData(1)
	h_nn = FormatPack.formatHex(h_nn)
	str = str .. FormatPack.getSpaceStr(col) .. h_nn .. '\n'
	--QQ长度
	local h_qq_len = hex:getDataA(4)
	local qq_len = hex:readInt() - 4
	h_qq_len = FormatPack.formatHex(h_qq_len) .. '  --QQ长度:' .. qq_len
	str = str .. FormatPack.getSpaceStr(col) .. h_qq_len .. '\n'
	--QQ号码
	local h_qq = hex:getData(qq_len)
	self.qq = lua_hex_to_asscii(h_qq)
	h_qq = FormatPack.formatHex(h_qq) .. '  --QQ:' .. self.qq
	str = str .. FormatPack.getSpaceStr(col) .. h_qq .. '\n'
	--包头包体
	local h_body = hex:getData(self.size - qq_len - 4 - 1 -1 - 4)
	-- local h_body_str = FormatPack.formatHex(h_body)
	-- self.fPack:pushStr(h_body_str,col)

	str = str .. FormatPack.getSpaceStr(col) .. self:headBodyFormat(col + 1,h_body) .. '\n'

	str = FormatPack.formatHex(data) .. '\n[' ..'\n' .. str .. ']\n'
	return str
end

--解密包头
function AndroidQQ:headBodyFormat(col,h_body)
	local data,key = lua_tea_decode(h_body)
	if key == '' then
		return FormatPack.getSpaceStr(col) .. '[无法解密]'
	else
		local str = FormatPack.getSpaceStr(col) .. '[decodeKey=' ..key .. '\n'
		  .. self:bodyFormat(col,data) .. FormatPack.getSpaceStr(col) ..']'
		return str
	end
end

--格式化包体
function AndroidQQ:bodyFormat(col,h_body)
	local hex = HexHelper:new(h_body)
	local str = ''
	-- --part1
	local h_part1_size = hex:getDataA(4)
	local part1_size = hex:readInt() - 4
	str = str .. FormatPack.getSpaceStr(col) .. FormatPack.formatHex(h_part1_size) .. '  --part1长度:' .. part1_size .. '\n'

	local h_part1 = hex:getData(part1_size)
	str = str .. self:part1Format(col,h_part1) .. '\n'

	--part2
	local h_part2_size = hex:getDataA(4)
	local part2_size = hex:readInt() - 4
	str = str .. FormatPack.getSpaceStr(col) .. FormatPack.formatHex(h_part2_size) .. '  --part2长度:' .. part2_size .. '\n'

	local h_part2 = hex:getData(part2_size)
	str = str .. self:part2Format(col,h_part2) .. '\n'

	return str
end

function AndroidQQ:part1Format(col,h_part)
	local s =  FormatPack.getSpaceStr(col) .. FormatPack.formatHex(h_part)
	return  s
end

function AndroidQQ:part2Format(col,h_part)
	local s = FormatPack.getSpaceStr(col) .. FormatPack.formatHex(h_part)
	return  s
end

return AndroidQQ
