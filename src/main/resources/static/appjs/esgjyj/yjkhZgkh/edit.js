$().ready(function() {
    selectdxLoad();
    selectzbLoad();
	validateRule();
});
function selectdxLoad() {
    var html = "";
    $.ajax({
        url : '/esgjyj/yjkhKhdx/list',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].id + '">' + data[i].name + '</option>'
            }
            $("#dxid").append(html);

        }
    });
}
function selectzbLoad() {
    var html = "";
    $.ajax({
        url : '/esgjyj/yjkhZbwh/list',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].id + '">' + data[i].zbmc+ '</option>'
            }
            $("#zbid").append(html);
            $("#zbid").chosen({
                maxHeight : 200
            });
        }
    });
}
$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/esgjyj/yjkhZgkh/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名字"
			}
		}
	})
}

var wrapper = new Vue({
    el:'#esapp',
    data:{
    	yjkhZgkh:'',
		optionsDx:''
	},
    methods:{
        initForm:function(){
            $.getJSON('/esgjyj/yjkhZgkh/get/'+GetQueryString('id'),function(res){
                wrapper.yjkhZgkh = res;
            })
        }
    },
    mounted:function(){
        this.initForm();
    }
})