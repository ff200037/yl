<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
        body, html {width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
        #allmap{width:100%;height:500px;}
        p{margin-left:5px; font-size:14px;}
    </style>
    <script type="text/javascript" src="http://api.map.baidu.com/getscript?v=2.0&ak=gsq1ccwGxXtG4wGmHTdE8LM4uQlRIyKc"></script>
    <title>圆形区域搜索</title>
</head>
<body>
    <div id="allmap"></div>
    <p>返回北京市地图上圆形覆盖范围内的“餐馆”检索结果，并展示在地图上</p>
</body>
</html>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("allmap");            // 创建Map实例
    var mPoint = new BMap.Point(116.404, 39.915);  
    map.enableScrollWheelZoom();
    map.centerAndZoom(mPoint,15);

    var circle = new BMap.Circle(mPoint,1000,{fillColor:"blue", strokeWeight: 1 ,fillOpacity: 0.3, strokeOpacity: 0.3,enableEditing:true});
    map.addOverlay(circle);
    var removecircle = function(e,ee,marker){
      //取得地图上所有的覆盖物
      var overlays = map.getOverlays();
      //取得圆形区域
      var bounds = marker.getBounds();
      var maker_arr = [];
      for(var i=0;i<overlays.length;i++){
        //判断 覆盖物为标注的并且是在圆形区域内部的
       if(overlays[i].uQ == "Marker"){
            //获取标注点到圆心的距离 与半径做对比
            if(map.getDistance(marker.getCenter(),overlays[i].getPosition()) < marker.getRadius()){
              maker_arr.push(overlays[i]);
            }
        } 
      }
      var r=confirm("你确定要删除区域中"+(maker_arr.length-1)+"个标注吗？");

      if (r==true){
        for(var i=0;i<maker_arr.length;i++){
           map.removeOverlay(maker_arr[i]);
        }
        map.removeOverlay(marker);
      }else{
         map.removeOverlay(marker);
      }
    }
    //创建右键菜单
    var circleMenu=new BMap.ContextMenu();
    circleMenu.addItem(new BMap.MenuItem('删除',removecircle.bind(removecircle))); 
      circle.addContextMenu(circleMenu);
  var local =  new BMap.LocalSearch(map, {renderOptions: {map: map, autoViewport: false}});  
    //local.searchNearby('餐馆',mPoint,1000);
     function addMarker(point){
      var marker = new BMap.Marker(point);
      map.addOverlay(marker);
    }
    // 随机向地图添加25个标注
    var bounds = map.getBounds();
    var sw = bounds.getSouthWest();
    var ne = bounds.getNorthEast();
    var lngSpan = Math.abs(sw.lng - ne.lng);
    var latSpan = Math.abs(ne.lat - sw.lat);
    for (var i = 0; i < 5; i ++) {
        var point = new BMap.Point(sw.lng + lngSpan * (Math.random() * 0.7), ne.lat - latSpan * (Math.random() * 0.7));
        addMarker(point);
    }
</script>