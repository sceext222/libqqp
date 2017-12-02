require('init')

local protoTab = {}

function loadLua(fileName)
	local endWith = 'Proto'
	if string.sub(fileName,-string.len(endWith)) == endWith then
		local p = require(fileName)
		protoTab[p.getName()] = p
	end
	return "ok"
end

--分析框右键lua分析
function formatHandler(isSend,data)
	for k, v in pairs(protoTab) do
		if v.isProto(isSend,data,-1,-1) then
			return v.formatHandler(isSend,data)
		end
	end
	return ''
end

--取出当前类包的类型
function getProtoName(isSend,data,protoType,port)
	for k, v in pairs(protoTab) do
		if v.isProto(isSend,data,protoType,port) then
			return v.getName()
		end
	end
	return ''
end

--重组包
function packHandler(isSend,data,protoName)
	--通过 getProtoName 得到名字才会使用此方法组包
	local p = protoTab[protoName]
	if p then
		return p.packHandler(isSend,data)
	end
	return '1'
end

--点击列表栏lua分析[抓包界面显示]
function luaHandler(isSend,data,protoName)
	--通过 getProtoName 得到名字才会使用此方法组包
	local p = protoTab[protoName]
	if p then
		return p.luaHandler(isSend,data)
	end
	return ''
end

--列表栏当前包详情
function getDescHandler(isSend,data,protoName)
	--通过 getProtoName 得到名字才会使用此方法组包
	local p = protoTab[protoName]
	if p then
		return p.getDescHandler(isSend,data)
	end
	return ''
end

--导出函数
--lua_hex_to_byte(str)
--lua_hex_to_short(str)
--lua_hex_to_int(str)
--lua_hex_to_long(str)
--lua_log(str) 日志文件debug.log
--lua_hex_to_asscii(str)
--lua_msg_box(str)
--lua_decode_protobuf
