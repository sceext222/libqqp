HexHelper = {}

function HexHelper:new(data)
	local o = {}
	o.data = string.sub(data,1)
	o.size = string.len(data) / 2
	o.pos = 1
    setmetatable(o, self)
	self.__index = self
	return o
end

function HexHelper:getDataA(hexLex)
	local hex  = string.sub(self.data,self.pos,self.pos + hexLex * 2 - 1)
	return hex
end

function HexHelper:getData(hexLex)
	local hex  = string.sub(self.data,self.pos,self.pos + hexLex * 2 - 1)
	self.pos = self.pos + hexLex * 2
	return hex
end

function HexHelper:readByte()
	return lua_hex_to_byte(self:getData(1))
end

function HexHelper:readShort()
	return lua_hex_to_short(self:getData(2))
end

function HexHelper:readInt()
	return lua_hex_to_int(self:getData(4))
end

function HexHelper:readLong()
	return lua_hex_to_long(self:getData(8))
end

function HexHelper:reset()
	self.pos = 1
end

return HexHelper
