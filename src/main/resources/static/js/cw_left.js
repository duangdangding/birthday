var visitor = "仙女小姐姐";
//根据分辨率调整小人位置
var addHeight;
if (window.screen.height == '1080') {
	addHeight = 350;
} else {
	addHeight = 150;
}

//鼠标在上方时
var quanMsg = [];
jQuery(document).ready(function ($) {
	//$(".mumu").jrumble({rangeX: 2,rangeY: 2,rangeRot: 1});
	$(".mumu").mouseover(function () {
		$(".mumu").fadeTo("300", 0.3);
		quanMsg = ["我隐身了，你看不到我", "我会隐身哦！嘿嘿！", "别动手动脚的，把手拿开！", "把手拿开我才出来！"];
		var i = Math.floor(Math.random() * quanMsg.length);
		showMessage(quanMsg[i]);
	});
	$(".mumu").mouseout(function () {
		$(".mumu").fadeTo("300", 1)
	});
});

//开始
jQuery(document).ready(function ($) {
	var now = (new Date()).getHours();
	if (now > 0 && now <= 6) {
		showMessage(visitor + ' 你是夜猫子呀？还不睡觉，明天起的来么你？', 6000);
	} else if (now > 6 && now <= 11) {
		showMessage(visitor + ' 早上好，早起的鸟儿有虫吃噢！早起的虫儿被鸟吃，你是鸟儿还是虫儿？嘻嘻！', 6000);
	} else if (now > 11 && now <= 14) {
		showMessage(visitor + ' 中午了，吃饭了么？不要饿着了，饿死了谁来挺我呀！', 6000);
	} else if (now > 14 && now <= 18) {
		showMessage(visitor + ' 中午的时光真难熬！还好有你在！晚饭也好香啊~', 6000);
	} else {
		showMessage(visitor + ' 快来逗我玩吧！', 6000);
	}
	$(".spig").animate({
		// top: $(".spig").offset().top + addHeight,
		top: $(".spig").offset().top + addHeight/2,
		left: 55
	}, {
		queue: false,
		duration: 1000
	});
});

//无聊讲点什么
jQuery(document).ready(function ($) {
	/*window.setInterval(function () {
        getPeopleCount();
    }, 100);*/
	window.setInterval(function () {
		var wumsgs = ["陪我聊天吧！", "好无聊哦，你都不陪我玩！", "喜欢就是喜欢，哪里来的欲擒故纵、克制隐忍，我巴不得一日三餐吃了什么都告诉你，路上遇见小猫舔爪子我也想要拍给你看。", "哇，漂亮的仙女姐姐~~~", 
			"我可爱吧！嘻嘻!~^_^!~~", "话不会说，但心是真的。。。","我给大家撒花儿了~~~","你能笑一下吗，我的咖啡忘记加糖了。", "你笑的时候我心里像被撒了一把跳跳糖。", "真是人间水蜜桃了呢~ 你就是人间的风花雪月。",
			"此女只应天上有，人间难得几回遇。", "岁月静好，很想和你就这样一起安然老去，不紧不慢，不慌不忙。", "就像画里的人，霎时间就飘了下来。", "我的愿望就是：你们都能开心幸福的过完这一生！",
			"我觉得世界上所有的褒义词都适合你。", "你不化妆是天生丽质，化了妆是仙女下凡。", "比今天的你更好看的只有明天的你！", "说吧，你为什么要下凡", "世界上最好看的人在我眼睛里，不信你看！",
			"今晚的星星和月亮都像你 遥不可及地好看。", "一颦一笑，一举一动，扣人心弦。", "不要以为你长得漂亮，身材好，大眼睛，双眼皮，说话声音好听，走路样子好看就可以随便欺负我。",
			"我从来都不骗人，如果我骗你了，那也骗的是仙女", "我觉得世界上就只有两种人能吸引人，一种是有智慧的，一种是漂亮的，而你是聪明并且又好看的。", "我想成为你最喜欢见到的和最不舍得说再见的人。",
			"喜欢我的扣1 不喜欢我的八千字说明理由", "认识你之后，一种智力上的优越感，油然而生。", "好多小姐姐呐~快来和我玩呀~~~", "是你们让我正能量满满呀！！！",
			"你在我的眼中，你就是我的人间万象，有你在，何处都是人间。","真想一直就这样对你好，不多想，不求结果，没有目的，不问往后，就这样，顺着时间的脉络，日复一日地温柔下去。",
			"我看到好的东西，第一个要分享的就是你。关于余生，我想要分享的，也是你。", "尽管你身材纤弱娇小，说话柔声细气，然而却很有力量，这是一种真正的精神美！", "哥哥美得像朵花，我弹起心爱的土琵琶。",
			"你是吃可爱长大的吧！", "可盐可甜，一眼万年~", "还不多吃点，你看看你都瘦成啥样了？", "哇，我是如此幸运，遇到你们，认识你们。","6根蜡烛代表着我的祝福：祝愿大家平平安安~~~",
			"生活很讨厌，还好你够可爱。", "你这么可爱，反抗是不敢反抗的，就偷偷略略略这样子", "天哪，仙女仙男们扎堆进来了！！！好嗨呦"];
		var i = Math.floor(Math.random() * wumsgs.length);
		danmuPool.push(wumsgs[i]);
		showMessage(wumsgs[i], 8000);
	}, 15000);
});

//鼠标点击时
jQuery(document).ready(function ($) {
	var stat_click = 0;
	$(".mumu").click(function () {
		stat_click++;
		if (stat_click > 4) {
			quanMsg = ["你有完没完呀？", "不要以为你长得漂亮，身材好，大眼睛，双眼皮，说话声音好听，走路样子好看就可以随便欺负我。", "你已经摸我" + stat_click + "次了", "非礼呀！救命！OH，My ladygaga"];
		} else {
			quanMsg = ["筋斗云！~我飞！", "我跑呀跑呀跑！~~", "别摸我，有什么好摸的！", "惹不起你，我还躲不起你么？", "不要摸我了，我会告诉主人来打你的！", "干嘛动我呀！小心我咬你！"];
		}
		var s = [0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.75, -0.1, -0.2, -0.3, -0.4, -0.5, -0.6, -0.7, -0.75];
		var i1 = Math.floor(Math.random() * s.length);
		var i2 = Math.floor(Math.random() * quanMsg.length);
		$(".spig").animate({
			left: document.body.offsetWidth / 2 * (1 + s[i1]),
			top: document.body.offsetHeight / 2 * (1 + s[i1])
		}, {
			duration: 500,
			complete: showMessage(quanMsg[i2])
		});
	});
});

//显示消息函数
function showMessage(a, b) {
	if (b == null) b = 1000;
	jQuery("#message").hide().stop();
	jQuery("#message").html(a);
	jQuery("#message").fadeIn();
	jQuery("#message").fadeTo("1", 1);
	jQuery("#message").fadeOut(b);
};

//拖动
var _move = false;
var ismove = false; //移动标记
var _x, _y; //鼠标离控件左上角的相对位置
jQuery(document).ready(function ($) {
	$("#spig").mousedown(function (e) {
		_move = true;
		_x = e.pageX - parseInt($("#spig").css("left"));
		_y = e.pageY - parseInt($("#spig").css("top"));
	});
	$(document).mousemove(function (e) {
		if (_move) {
			var x = e.pageX - _x;
			var y = e.pageY - _y;
			var wx = $(window).width() - $('#spig').width();
			var dy = $(document).height() - $('#spig').height();
			if (x >= 0 && x <= wx && y > 0 && y <= dy) {
				$("#spig").css({
					top: y,
					left: x
				}); //控件新位置
				ismove = true;
			}
		}
	}).mouseup(function () {
		_move = false;
	});
});
<!-- 移动端移动事件 -->
$(function () {
	var cont = $("#spig");
	var contW = $("#spig").width();
	var contH = $("#spig").height();
	var startX, startY, sX, sY, moveX, moveY;
	var winW = $(window).width();
	var winH = $(window).height();

	cont.on({//绑定事件
		touchstart: function (e) {
			startX = e.originalEvent.targetTouches[0].pageX;    //获取点击点的X坐标    
			startY = e.originalEvent.targetTouches[0].pageY;    //获取点击点的Y坐标
			sX = $(this).offset().left;//相对于当前窗口X轴的偏移量
			sY = $(this).offset().top;//相对于当前窗口Y轴的偏移量
			leftX = startX - sX;//鼠标所能移动的最左端是当前鼠标距div左边距的位置
			rightX = winW - contW + leftX;//鼠标所能移动的最右端是当前窗口距离减去鼠标距div最右端位置
			topY = startY - sY;//鼠标所能移动最上端是当前鼠标距div上边距的位置
			bottomY = winH - contH + topY;//鼠标所能移动最下端是当前窗口距离减去鼠标距div最下端位置                
		},
		touchmove: function (e) {
			e.preventDefault();
			moveX = e.originalEvent.targetTouches[0].pageX;//移动过程中X轴的坐标
			moveY = e.originalEvent.targetTouches[0].pageY;//移动过程中Y轴的坐标
			if (moveX < leftX) {
				moveX = leftX;
			}
			if (moveX > rightX) {
				moveX = rightX;
			}
			if (moveY < topY) {
				moveY = topY;
			}
			if (moveY > bottomY) {
				moveY = bottomY;
			}
			$(this).css({
				"left": moveX + sX - startX,
				"top": moveY + sY - startY,
			})
		},

	})

})