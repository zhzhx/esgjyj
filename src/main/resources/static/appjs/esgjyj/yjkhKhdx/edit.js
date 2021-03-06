$().ready(function() {
	validateRule();
});

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
		url : "/esgjyj/yjkhKhdx/update",
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
    	yjkhKhdx:'',
        options: [
            { text: '审执部门法官', value: '1' },
            { text: '审执部门庭长', value: '2' },
            { text: '综合部门法官', value: '3' },
            { text: '法官助理', value: '4' },
            { text: '书记员', value: '5' },
            { text: '综合部门法官助理', value: '6' },
            { text: '综合部门书记员', value: '7' },
            { text: '综合部门主任', value: '8' },
            { text: '书记员（聘任制）', value: '9' }
        ]
	},
    methods:{
        initForm:function(){
            $.getJSON('/esgjyj/yjkhKhdx/get/'+GetQueryString('id'),function(res){
                wrapper.yjkhKhdx = res;
            })
        }
    },
    mounted:function(){
        this.initForm();
    }
})



var openUser = function(){
    layer.open({
        type:2,
        title:"选择人员",
        area : [ '300px', '450px' ],
        content:"/userTree.html"
    })
}
var openOffice = function(){
    layer.open({
        type:2,
        title:"选择部门",
        area : [ '300px', '450px' ],
        content:"/officeTree.html"
    })
}
function loadUser(id,username){
    $("#userid").val(id);
    $("#username").val(username);
}
function loadOffice(id,name){
    $("#officeid").val(id);
    $("#officeName").val(name);
}