<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
<style type="text/css">
html {
	height: 100%
}

body {
	height: 100%;
	margin: 0px;
	padding: 0px
}

#container {
	height: 100%
}
</style>
<script type="text/javascript"
	src="http://api.map.baidu.com/getscript?v=2.0&ak=gsq1ccwGxXtG4wGmHTdE8LM4uQlRIyKc"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/TextIconOverlay/1.2/src/TextIconOverlay_min.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/MarkerClusterer/1.2/src/MarkerClusterer_min.js"></script>
<script language="JavaScript" type="text/javascript"
	src="${pageContext.request.contextPath}/resource/js/jquery-2.0.1.min.js"></script>

<script type="text/javascript">
	var marker;
	var time = 0;
	var map;
	var results;
	window.onload = function() {
		map = new BMap.Map("container"); // 创建Map实例  
		map.centerAndZoom(new BMap.Point(110.3355859188, 20.0257920733), 12); // 初始化地图,设置中心点坐标和地图级别  
		map.enableScrollWheelZoom(); //启用滚轮放大缩小  
		map.setCurrentCity("海口"); // 设置地图显示的城市 此项是必须设置的
		//添加地图类型控件
		map.addControl(new BMap.MapTypeControl({
			mapTypes : [ BMAP_NORMAL_MAP, BMAP_HYBRID_MAP ]
		}));
		//点击地图并在此添加标记
		map.addEventListener("click", function(e) {//鼠标单击地图触发
			if (time > 0) {
				alert("请把已经标注的先删除");
				return;
			}
			popLayer(e); // 保留最后一个
		});
		// initMyMap();
	}

	function popLayer(e) {
		createMaker(e);
	}

	function createMaker(e) {
		$('#vvv-inputEl', window.parent.document).val('123');
		marker = new BMap.Marker(e.point);
		map.addOverlay(marker); // 添加删除不掉
		map.panTo(e.point);
		e.point.lng;
		displayPOI(e.point.lng, e.point.lat);
		/* marker.setAnimation(BMAP_ANIMATION_BOUNCE);//跳动的标记 */
		//创建右键菜单
		var markerMenu = new BMap.ContextMenu();
		markerMenu.addItem(new BMap.MenuItem('删除', removeMarker
		        .bind(marker)));
		marker.addContextMenu(markerMenu);
		//标注
		var text = "标注位置";
		var label = new BMap.Label(text, {
			offset : new BMap.Size(-85, -120)
		});
		//设置label(标注的样式)
		label.setStyle({
			color : "black",
			fontSize : "12px",
			height : "110px",
			//lineHeight : "20px",
			fontFamily : "微软雅黑",
			maxWidth : "none",
			border : "none"
		});
		// marker.setLabel(label);

		time = time + 1;
	}

	var removeMarker = function(e, ee, marker) {//删除标记
		map.removeOverlay(marker);
		var mPoint = new BMap.Point(e.lng, e.lat);
		map.removeOverlay(new BMap.Circle(mPoint,500));
		time = 0;
	}

	// 删除
	var removecircle = function(e, ee, marker) {
		//取得地图上所有的覆盖物
		var overlays = map.getOverlays();
		//取得圆形区域
		var bounds = marker.getBounds();
		var maker_arr = [];
		for (var i = 0; i < overlays.length; i++) {
			//判断 覆盖物为标注的并且是在圆形区域内部的
			if (overlays[i].uQ == "Marker") {
				//获取标注点到圆心的距离 与半径做对比
				if (map.getDistance(marker.getCenter(), overlays[i]
						.getPosition()) < marker.getRadius()) {
					maker_arr.push(overlays[i]);
				}
			}
		}
		var r = confirm("你确定要删除区域吗？");
		var marker_ = new BMap.Marker(marker.point);
		if (r == true) {
			for (var i = 0; i < maker_arr.length; i++) {
				map.removeOverlay(maker_arr[i]);
			}
			map.removeOverlay(marker);
		} else {
			map.removeOverlay(marker);
		}
	}

	var mOption = {
		poiRadius : 500, //半径为1000米内的POI,默认100米
		numPois : 12
	}

	// 画圆
	function displayPOI(lng, lat) {
		var mPoint = new BMap.Point(lng, lat);
		var circle = new BMap.Circle(mPoint, 500, {
			fillColor : "blue",
			strokeWeight : 1,
			fillOpacity : 0.3,
			strokeOpacity : 0.3,
			enableEditing : false
		});
		//创建右键菜单
		var circleMenu = new BMap.ContextMenu();
		circleMenu.addItem(new BMap.MenuItem('删除', removecircle
				.bind(removecircle)));
		circle.addContextMenu(circleMenu);
		map.centerAndZoom(mPoint, 16);
		map.enableScrollWheelZoom(); //启用滚轮缩放

		var bgc = new BMap.Geocoder(); //创建地址解析实例
		bgc.getLocation(mPoint, function(rs){
            var addComp = rs.addressComponents; 
            console.log(rs.address);//地址信息
         });
		map.addOverlay(circle); //添加一个圆形覆盖物
		bgc.getLocation(mPoint, function mCallback(rs) {
			var allPois = rs.surroundingPois; //获取全部POI（该点半径为100米内有6个POI点）
			for (i = 0; i < allPois.length; ++i) {
				// map.addOverlay(new BMap.Marker(allPois[i].point));                
			}
		}, mOption);
	}
	
	// 可是考虑设置救援车位置
	function initMyMap(runStatus) {
		var addFlag;
		if (runStatus == undefined) {
			addFlag = null;
		} else if (runStatus == "travel") {
			addFlag = "1";
		} else if (runStatus == "qiuet") {
			addFlag = "0";
		} else if (runStatus == "offLine") {
			addFlag = "5";
		}
		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/bis/map/queryCarData",
			data : {
				"runStatus" : addFlag
			},
			async : false,
			success : function(msg) {
				if (msg) {
					var pt;
					var markers = [];
					var results = msg.info;
					for (var i = 0; i < results.length; i++) {
						pt = createPoint(results[i].lon, results[i].lat);
						markers.push(createMarker(pt));
					}
					pointC(markers);
				}
			}
		});
	}
	var statusIcon = new BMap.Icon(
			"../../business/carAdministration/autoMap/images/auto-1.png",
			new BMap.Size(32, 32));
	
	function showInMap(lat, lon) {
		var point = new BMap.Point(lat, lon);
		map.centerAndZoom(point, 15);
		var marker = new BMap.Marker(point, {
			icon : statusIcon,
			enableDragging : true,
			raiseOnDrag : true
		}); // 创建标注
		map.addOverlay(marker); // 将标注添加到地图中
		console.info('---');
	}
	
	var markerClusterer = null;
	function pointC(markers1) {
		//map.clearOverlays();
		if (markerClusterer)
			markerClusterer.clearMarkers(); //此步骤需要  
		markerClusterer = new BMapLib.MarkerClusterer(map, {
			markers : markers1
		});
	}
	function createPoint(lon, lat) {
		return new BMap.Point(lon, lat);
	}
	function createMarker(point) {
		return new BMap.Marker(point, {
			icon : statusIcon
		});
	}
</script>
</head>
<body>
	<div id="container"></div>
</body>
</html>