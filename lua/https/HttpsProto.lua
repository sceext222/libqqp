HttpsProto = {}

function HttpsProto.getName()
	return "HTTPS"
end

function HttpsProto.isProto(isSend,data,protoType,port)
		if isSend then
		if string.sub(data,1,6) == '160301' then
			return true
		end
	else

	end

	return false
end

--重组包
function HttpsProto.packHandler(isSend,data)
	return '1'
end

--点击列表栏lua分析[抓包界面显示]
function HttpsProto.luaHandler(isSend,data)
	local str = FormatPack.formatHex(data) .. "\r\n"
	return str
end

--列表栏当前包详情
function HttpsProto.getDescHandler(isSend,data)
	return 'http'
end

function HttpsProto.formatHandler(isSend,data)
	--通过 getProtoName 得到名字才会使用此方法组包
	return ''
end

return HttpsProto
