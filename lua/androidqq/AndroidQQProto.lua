AndroidQQProto = {}

function AndroidQQProto.getName()
	return "安卓QQ"
end

function AndroidQQProto.isProto(isSend,data,protoType,port)
	--安卓QQ发送 00 00 02 FB 00 00 00 08 02 00 00 00 04 00 00 00
	--安卓QQ接收 00 00 04 4F 00 00 00 08 02 00 00 00 00 0D 34 31
	local hex = HexHelper:new(data)
	--判断安卓QQ
	if hex.size > 7 then
		local totalLen = hex:readInt()
		if totalLen > 0 and totalLen <= hex.size then
			if hex:getData(3) == '000000' and  protoType == '6' then
				--安卓QQ
				return true
			end
		end
	end
	return false
end

--重组包
function AndroidQQProto.packHandler(isSend,data)
	local hex = HexHelper:new(data)
	local h_len = lua_hex_to_int(hex:getData(4))
	if h_len  > hex.size then
		return ''
	end
	return '1'
end

--点击列表栏lua分析[抓包界面显示]
function AndroidQQProto.luaHandler(isSend,data)
	--通过 getProtoName 得到名字才会使用此方法组包
	return ''
end

--列表栏当前包详情
function AndroidQQProto.getDescHandler(isSend,data)
	--通过 getProtoName 得到名字才会使用此方法组包
	lua_log("haaahaha")
	return ''
end

function AndroidQQProto.formatHandler(isSend,data)
	--通过 getProtoName 得到名字才会使用此方法组包
	lua_log("xxx")
	return ''
end

return AndroidQQProto
