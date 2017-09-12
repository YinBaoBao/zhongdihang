(function () {
	var $ax = function (api, success, error) {
		this.url = Bank.route(api);
		this.type = api.method;
		this.data = {};
		this.dataType = "json";
		this.async = false;
		this.success = success;
		this.error = error;
		this.contentType='application/x-www-form-urlencoded;charset=UTF-8';
	};
	$ax.prototype = {
		start : function () {	
			var me = this;
			
			if (this.url.indexOf("?") == -1) {
				this.url = this.url + "?jstime=" + new Date().getTime();
			} else {
				this.url = this.url + "&jstime=" + new Date().getTime();
			}
			
			$.ajax({
		        type: this.type,
		        url: this.url,
		        dataType: this.dataType,
				ContentType:this.contentType,
		        async: this.async,
		        data: this.data,
				beforeSend: function(request) {
					request.setRequestHeader("token", Bank.getHeader());
				},
		        success: function(data) {
					if(data.code==1000){
						me.success(data.content);
					}else if(data.code==4006){
						//参数验证失败
					}else if(data.code==4003||data.code==4004){
						parent.layer.msg(data.message, {
							time: 3000
						},function(){
							if(this.url.indexOf("auth")<0) {
								Bank.goto(Bank.page.login);
							}
						});
					}else if(data.code=4000){
						Bank.error(data.message);
					}
		        },
		        error: function(data) {
					if(data.status==403){
						Bank.error(data.responseJSON.message);
					}
					if(data.status==401){
						parent.layer.msg('当前未登录，请先登录', {
							time: 3000 //2秒关闭（如果不配置，默认是3秒）
						}, function(){
							Bank.goto(Bank.page.login);
						});
					}
					if(data.status==500){
						Bank.error(data.responseJSON.message);
					}
		        }
		    });
		},
		set : function (key, value) {
			if (typeof key == "object") {
				for (var i in key) {
					if (typeof i == "function")
						continue;
					this.data[i] = key[i];
				}
			} else {
				this.data[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
			}
			return this;
		},
		jsonData:function(data){
			this.setContentType("application/json;charset=UTF-8");
			this.data=JSON.stringify(data);
			return this;
		},
		setContentType:function(contentType){
			$.ajaxSetup({
				contentType : contentType
			});
		},
		setData : function(data){
			this.data = data;
			return this;
		},
		clear : function () {
			this.data = {};
			return this;
		},
		post:function(){
			this.type="POST";
			return this;
		},
		get:function(){
			this.type="GET";
			return this;
		},
		put:function(){
			this.type="PUT";
			return this;
		},
		delete:function(){
			this.type="DELETE";
			return this;
		}
	};
	window.$ax = $ax;
} ());

function selectData(dictCode){
	var dictData;
	var ajax=new $ax(Bank.api.dict_select, function (data) {
		dictData=data;
	}, function (data) {
		Bank.error("查询不到字典"+dictCode);
	});
	ajax.set("dictCode",dictCode);
	ajax.start();
	return dictData;
}