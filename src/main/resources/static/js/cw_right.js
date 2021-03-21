// 萌娘
// var chongwu = "https://unpkg.com/live2d-widget-model-shizuku@1.0.5/assets/shizuku.model.json";
// 小可爱
// var chongwu = "https://unpkg.com/live2d-widget-model-koharu@1.0.5/assets/koharu.model.json";
// 初音
var chongwu = "https://unpkg.com/live2d-widget-model-miku@1.0.5/assets/miku.model.json";
L2Dwidget.init({
	"model": {
		jsonPath:
		chongwu,
		"scale": 1
	}, "display": {
		"position": "right", "width": 110, "height": 150,
		"hOffset": 0, "vOffset": -20
	}, "mobile": {"show": true, "scale": 0.5},
	"react": {"opacityDefault": 0.8, "opacityOnHover": 0.1}
});