PcQQProto = {}

function PcQQProto.getName()
	return "电脑QQ"
end

function PcQQProto.isProto(isSend,data,protoType,port)
	--发送 02 35 55 00 02 13 94 1A 98 4D E3 02 00 00 00 01 .....03
	--接收 02 35 55 00 65 05 0D 1A 98 4D E3 00 00 00 2E 1D .....03
	local xLen = string.len(data)
	if port == '8000' and xLen > 8 then
		if string.sub(data,1,2) == '02' and string.sub(data,xLen-1,xLen) == '03' then
			return true
		end
	end
	return false
end

--重组包
function PcQQProto.packHandler(isSend,data)
	return '1'
end

local CsCmdNo = require "pcqq/CsCmdNo";
local PubNo = require "pcqq/PubNo";

--点击列表栏lua分析[抓包界面显示]
function PcQQProto.luaHandler(isSend,data)
	local str = FormatPack.formatHex(data) .. "\r\n"
	local hex = HexHelper:new(data)

	local h_top = hex:getData(1)
	str = str .. FormatPack.formatHex(h_top) .. "\r\n"
	local h_ver = hex:getData(2)
	str = str .. FormatPack.formatHex(h_ver) .. "//版本" .. "\r\n"
	local h_cmd = hex:getData(2)
	str = str .. FormatPack.formatHex(h_cmd) .. "//命令" .. "\r\n"
	local h_seq = hex:getData(2)

	str = str .. FormatPack.formatHex(h_seq) .. '//序列'.. "\r\n"
	local h_qq = hex:getData(4)
	str = str .. FormatPack.formatHex(h_qq)  .. "//QQ:" .. string.format('%d',"0x" .. h_qq) ..  "\r\n"

	return str
end

--列表栏当前包详情
function PcQQProto.getDescHandler(isSend,data)
	local hex = HexHelper:new(data)

	local h_top = hex:getData(1)

	local h_ver = hex:getData(2)

	local h_cmd = hex:getData(2)

	local vers = PubNo[ lua_hex_to_int(h_ver) ] or "???";

	local cmds = CsCmdNo[ lua_hex_to_int(h_cmd) ] or "???";

	return  h_cmd .. ' ' .. cmds .. ' ' .. vers
end

function PcQQProto.formatHandler(isSend,data)
	--通过 getProtoName 得到名字才会使用此方法组包
	return ''
end

return PcQQProto
