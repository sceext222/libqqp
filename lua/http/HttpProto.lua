HttpProto = {}

function HttpProto.getName()
	return "HTTP"
end

function HttpProto.isProto(isSend,data,protoType,port)
	if isSend then
		--47 45 54 GET
        --50 4F 53 54 POST
		if string.sub(data,1,6) == '474554' then
			return true
		end

		if string.sub(data,1,8) == '504F5354' then
			return true
		end
	else
		if string.sub(data,1,8) == '48545450' then
			return true
		end
	end

	return false
end

--重组包
function HttpProto.packHandler(isSend,data)
	return '1'
end

--点击列表栏lua分析[抓包界面显示]
function HttpProto.luaHandler(isSend,data)
	local str = FormatPack.formatHex(data) .. "\r\n"
	return str
end

--列表栏当前包详情
function HttpProto.getDescHandler(isSend,data)
	return 'http'
end

function HttpProto.formatHandler(isSend,data)
	--通过 getProtoName 得到名字才会使用此方法组包
	return ''
end

return HttpProto
