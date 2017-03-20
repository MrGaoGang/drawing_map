/*************************************************************************
* Copyright (c) 2015 Lemberg Solutions
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
**************************************************************************/

package com.example.descriptmap;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.geocoder.RegeocodeRoad;
import com.amap.api.services.geocoder.StreetNumber;
import com.example.descriptmap.model.MapObjectContainer;
import com.example.descriptmap.model.MapObjectModel;
import com.example.descriptmap.popup.CquptPoint;
import com.example.descriptmap.popup.InitPoints;
import com.example.descriptmap.popup.TextPopup;
import com.ls.widgets.map.MapWidget;
import com.ls.widgets.map.config.GPSConfig;
import com.ls.widgets.map.config.MapGraphicsConfig;
import com.ls.widgets.map.config.OfflineMapConfig;
import com.ls.widgets.map.events.MapScrolledEvent;
import com.ls.widgets.map.events.MapTouchedEvent;
import com.ls.widgets.map.events.ObjectTouchEvent;
import com.ls.widgets.map.interfaces.Layer;
import com.ls.widgets.map.interfaces.MapEventsListener;
import com.ls.widgets.map.interfaces.OnLocationChangedListener;
import com.ls.widgets.map.interfaces.OnMapDoubleTapListener;
import com.ls.widgets.map.interfaces.OnMapLongClickListener;
import com.ls.widgets.map.interfaces.OnMapScrollListener;
import com.ls.widgets.map.interfaces.OnMapTouchListener;
import com.ls.widgets.map.model.MapObject;
import com.ls.widgets.map.utils.GeoUtils;
import com.ls.widgets.map.utils.PivotFactory;
import com.ls.widgets.map.utils.PivotFactory.PivotPosition;

public class BrowseMapActivity extends AppCompatActivity
implements MapEventsListener,
		   OnMapTouchListener
{
	private static final String TAG = "BrowseMapActivity";
	
	private static final Integer LAYER1_ID = 0;
	private static final Integer LAYER2_ID = 1;
	private static final int MAP_ID = 23;

	private int nextObjectId;
	private int pinHeight;
	
	private MapObjectContainer model;
	private MapWidget map;
	private TextPopup mapObjectInfoPopup;
	
	private Location points[];
	private int currentPoint;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        nextObjectId = 0;
        
        model = new MapObjectContainer();
        
        initTestLocationPoints();
        initMap(savedInstanceState);
        initModel();
        initMapObjects();
        initMapListeners();
        
        // Will show the position of the user on a map.
        // Do not forget to enable ACCESS_FINE_LOCATION and ACCESS_COARSE_LOCATION permission int the manifest.
        
       // Uncomment this if you are at Filitheyo island :) 
       // map.setShowMyPosition(true);

         map.centerMap();
    }
    
    
    @Override
	protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    	map.saveState(outState);
	}

    private void initTestLocationPoints() 
	{
		points = new Location[68];
		for (int i=0; i<points.length; ++i) {
			InitPoints point=new InitPoints();
			points[i]=point.getLocation(i);
		}

		currentPoint = 0;
	}
	
	
	private Location getNextLocationPoint()
	{
		if (currentPoint < points.length-1) {
			currentPoint += 1;
		} else {
			currentPoint = 0;
		}
		
		return points[currentPoint];
	}


	private void initMap(Bundle savedInstanceState) 
	{
		// In order to display the map on the screen you will need 
		// to initialize widget and place it into layout.
        map = new MapWidget(savedInstanceState, this, 
							  "map", // root name of the map under assets folder.
							  11); // initial zoom level

        map.setId(MAP_ID);
 
        OfflineMapConfig config = map.getConfig();
        config.setPinchZoomEnabled(true); // Sets pinch gesture to zoom
        config.setFlingEnabled(true);    // Sets inertial scrolling of the map
        config.setMaxZoomLevelLimit(20);
        config.setZoomBtnsVisible(true); // Sets embedded zoom buttons visible
        
        // Configuration of GPS receiver
        GPSConfig gpsConfig = config.getGpsConfig();
        gpsConfig.setPassiveMode(false);
        gpsConfig.setGPSUpdateInterval(500, 5);
        
        // Configuration of position marker
        MapGraphicsConfig graphicsConfig = config.getGraphicsConfig();
        graphicsConfig.setAccuracyAreaColor(0x550000FF); // Blue with transparency
        graphicsConfig.setAccuracyAreaBorderColor(Color.BLUE); // Blue without transparency

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.rootLayout);
        // Adding the map to the layout
        layout.addView(map, 0);
        
        // Adding layers in order to put there some map objects
        map.createLayer(LAYER1_ID); // you will need layer id's in order to access particular layer
        map.createLayer(LAYER2_ID);
		//创建图层
	}
	
	
	private void initModel()
	{
		// Adding objects to the model
		// You may want to implement your own model 
		MapObjectModel objectModel = new MapObjectModel(0, 100, 100, "第一个Map对象点");
		model.addObject(objectModel);
		objectModel = new MapObjectModel(1, 600, 350, "第二个Map对象点");//直接输入坐标的方式得到地图对象
		model.addObject(objectModel);
		objectModel = new MapObjectModel(2, 700, 350, "第三个Map对象点");
		model.addObject(objectModel);
		
		int id = 3;
		for (Location point:points) {
			objectModel = new MapObjectModel(id, point, "Point " + id);//通过定位得到的地图对象
			model.addObject(objectModel);
			id += 1;
		}
		
	}


	private void initMapObjects() 
	{	
		
		mapObjectInfoPopup = new TextPopup(this, (RelativeLayout)findViewById(R.id.rootLayout));
		//得到图层
		Layer layer1 = map.getLayerById(LAYER1_ID);
		Layer layer2 = map.getLayerById(LAYER2_ID);
		
		for (int i=0; i<model.size(); ++i) {
			addNotScalableMapObject(model.getObject(i), layer1);
		}
		
		// Adding two map objects to the second layer
		addNotScalableMapObject(800, 300, layer2);
		addNotScalableMapObject(900, 350, layer2);
	}

	//不可以缩放的地图对象,也就是地图上的点大小不变化，跟着地图的变化而变化
	private void addNotScalableMapObject(int x, int y,  Layer layer) 
	{
		// Getting the drawable of the map object
		Drawable drawable = getResources().getDrawable(R.mipmap.map_object);
		pinHeight = drawable.getIntrinsicHeight();
		// Creating the map object
		MapObject object1 = new MapObject(Integer.valueOf(nextObjectId), // id, will be passed to the listener when user clicks on it 
										  drawable,  
										  new Point(x, y), // coordinates in original map coordinate system.
										  // Pivot point of center of the drawable in the drawable's coordinate system.
										  PivotFactory.createPivotPoint(drawable, PivotPosition.PIVOT_CENTER),
										  true, // This object will be passed to the listener
										  false); // is not scalable. It will have the same size on each zoom level

		// Adding object to layer
		layer.addMapObject(object1);
		nextObjectId += 1;
	}
	
	
	private void addNotScalableMapObject(MapObjectModel objectModel,  Layer layer) 
	{
		if (objectModel.getLocation() != null) {
			addNotScalableMapObject(objectModel.getLocation(), layer);
		} else {
			addNotScalableMapObject(objectModel.getX(), objectModel.getY(),  layer);
		}
	}

	
	private void addNotScalableMapObject(Location location, Layer layer) {
		if (location == null)
			return;
		
		// Getting the drawable of the map object
		Drawable drawable = getResources().getDrawable(R.mipmap.map_object);
		// Creating the map object
		MapObject object1 = new MapObject(Integer.valueOf(nextObjectId), // id, will be passed to the listener when user clicks on it 
										  drawable,  
										  new Point(0, 0), // coordinates in original map coordinate system.
										  // Pivot point of center of the drawable in the drawable's coordinate system.
										  PivotFactory.createPivotPoint(drawable, PivotPosition.PIVOT_CENTER),
										  true, // This object will be passed to the listener
										  false); // is not scalable. It will have the same size on each zoom level
		layer.addMapObject(object1);
		
		// Will crash if you try to move before adding to the layer. 
		object1.moveTo(location);
		nextObjectId += 1;
	}

//这个方法的意思是让地图对象不随着地图的变化而变化，而是一直保持着对象原来的大小
	private void addScalableMapObject(int x, int y, Layer layer) 
	{
		Drawable drawable = getResources().getDrawable(R.mipmap.map_object);
		MapObject object1 = new MapObject(Integer.valueOf(nextObjectId), 
										  drawable, 
										  x, 
										  y, 
										  true, 
										  true);//不随着地图的变化而变化，也就是保持原来的大小

		layer.addMapObject(object1);
		nextObjectId += 1;
	}
	

	private void initMapListeners() 
	{
		// In order to receive MapObject touch events we need to set listener
		map.setOnMapTouchListener(this);
		
		// In order to receive pre and post zoom events we need to set MapEventsListener
		//为了在放大前后执行一些操作，你需要添加MapEventsListener 的实例到MapWidget
		// ，使用MapWidget.removeMapEventsListener移除该监听器。，这个监听器还有其他的
		//方法比如onPreZoomOut/OnPreZoonIn等等
        map.addMapEventsListener(this); 
        
        // In order to receive map scroll events we set OnMapScrollListener

        map.setOnMapScrolledListener(new OnMapScrollListener()
        {
            public void onScrolledEvent(MapWidget v, MapScrolledEvent event)
            {
                handleOnMapScroll(v, event);
            }
        });
        
        //位置发生改变，当位置发生改变的时候跳转到对应的位置
        map.setOnLocationChangedListener(new OnLocationChangedListener() {
			@Override
			public void onLocationChanged(MapWidget v, Location location) {
				// You can handle location change here.
				// For example you can scroll to new location by using v.scrollMapTo(location)
				/**
                 *    Layer layer = v.getLayerById(LAYER_ID);
				 MapObject object = layer.getMapObject(OBJECT_ID);
				 object.moveTo(location);
				 */

			}
		});
		//双击添加地图对象
		map.setOnDoubleTapListener(new OnMapDoubleTapListener() {
			@Override
			public boolean onDoubleTap(MapWidget mapWidget, MapTouchedEvent mapTouchedEvent) {
				Location location=addObjetWhereTouched(map,mapTouchedEvent,R.mipmap.map_object);
					CquptPoint cquptPoint=new CquptPoint();
					String address= cquptPoint.getLocation(location);
				showAlertDialog("位置:   "+address+"   纬度 latitude="+location.getLatitude()+"经度(laongtitude):= "+location.getLongitude());
					return true;
			}
		});
		//长按也可以添加地图对象
		map.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View view) {

				Toast.makeText(BrowseMapActivity.this,"长按不可添加地图对象", Toast.LENGTH_SHORT).show();
				return true;
			}
		});
		map.setOnMapLongClickListener(new OnMapLongClickListener() {
			@Override
			public boolean onLongClick(MapWidget mapWidget, MapTouchedEvent mapTouchedEvent) {
				if (mapTouchedEvent.getTouchedObjectIds().size() == 0)
				{
				Location location=	addObjetWhereTouched(mapWidget,mapTouchedEvent,R.mipmap.map_object);
					Toast.makeText(BrowseMapActivity.this, "长按地图，新添加的地图对象 coords: Lat: " + location.getLatitude() + " Lon:" + location.getLongitude(), Toast.LENGTH_SHORT).show();
					showLocation show=new showLocation(location);
					show.execute();
				}
				else
				{
					Toast.makeText(BrowseMapActivity.this, "Layer Id: " + mapTouchedEvent.getTouchedObjectIds().get(0).getLayerId(), Toast.LENGTH_SHORT).show();
				}
				return true;
			}
		});
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.menu, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) {
		case R.id.zoomIn:
			map.zoomIn();
			return true;
		case R.id.zoomOut:
			map.zoomOut();
			return true;
		case R.id.hideLayer2: {
			Layer layer = map.getLayerById(LAYER2_ID);
			if (layer != null) {
				layer.setVisible(false);
				map.invalidate(); // Need to repaint the layer. This is a bug and will be fixed in next version.
			}
			return true;
			}
		case R.id.showLayer2: {
			Layer layer = map.getLayerById(LAYER2_ID);
			if (layer != null) {
				layer.setVisible(true);
				map.invalidate(); // Need to repaint the layer. This is a bug and will be fixed in next version.
			}
			return true;
			}
		
		case R.id.scroll_next:
			map.scrollMapTo(getNextLocationPoint());//从一个对象点，滑翔到另一个对象点，可以使用这个来做一个
			//用户根据自己的需要来一次得到自己所发布的信息
			break;
			
		}
		
		return super.onOptionsItemSelected(item);
	}


	private void handleOnMapScroll(MapWidget v, MapScrolledEvent event) 
	{	
		// When user scrolls the map we receive scroll events
		// This is useful when need to move some object together with the map
		
		int dx = event.getDX(); // Number of pixels that user has scrolled horizontally
		int dy = event.getDY(); // Number of pixels that user has scrolled vertically
		
		if (mapObjectInfoPopup.isVisible()) {
			mapObjectInfoPopup.moveBy(dx, dy);
		}
	}
	
	
	
	@Override
	public void onPostZoomIn() 
	{
		Log.i(TAG, "onPostZoomIn()");
	}

	@Override
	public void onPostZoomOut() 
	{
		Log.i(TAG, "onPostZoomOut()");		
	}

	@Override
	public void onPreZoomIn() 
	{
		Log.i(TAG, "onPreZoomIn()");
		
		if (mapObjectInfoPopup != null) {
			mapObjectInfoPopup.hide();//在放大地图的时候，需要将已经显示了的信息将其隐藏起来
		}	
	}

	@Override
	public void onPreZoomOut() 
	{
		Log.i(TAG, "onPreZoomOut()");		
		
		if (mapObjectInfoPopup != null) {
			mapObjectInfoPopup.hide();//再缩小地图的时候也需要将已经显示了的信息隐藏起来
		}	
	}


	//* On map touch listener implemetnation *//
	@Override
	public void onTouch(MapWidget v, MapTouchedEvent event) 
	{
		// Get touched object events from the MapTouchEvent
		ArrayList<ObjectTouchEvent> touchedObjs = event.getTouchedObjectIds();
		//当有多个点击事件的时候
		if (touchedObjs.size() > 0) {
			
			int xInMapCoords = event.getMapX();
			int yInMapCoords = event.getMapY();
			int xInScreenCoords = event.getScreenX();
			int yInScreenCoords = event.getScreenY();
			//得到最新点击的那个事件
			ObjectTouchEvent objectTouchEvent = event.getTouchedObjectIds().get(0);
			
			// Due to a bug this is not actually the layer id, but index of the layer in layers array.
			// Will be fixed in the next release.
			long layerId = objectTouchEvent.getLayerId();
			Integer objectId = (Integer)objectTouchEvent.getObjectId();
			// User has touched one or more map object
			// We will take the first one to show in the toast message.
			String message = "You touched the object with id: " + objectId + " on layer: " + layerId + 
			" mapX: " + xInMapCoords + " mapY: " + yInMapCoords + " screenX: " + xInScreenCoords + " screenY: " + 
			yInScreenCoords;

			Toast.makeText(BrowseMapActivity.this,message,Toast.LENGTH_SHORT).show();
				
			MapObjectModel objectModel = model.getObjectById(objectId.intValue());
					
			if (objectModel != null) {
				// This is a case when we want to show popup info exactly above the pin image
				
		        float density = getResources().getDisplayMetrics().density;
		        int imgHeight = (int) (pinHeight / density / 2);

				/***
				 * 如何地图对象的坐标是自己用像素设定的，也就是getX()！=0的情况下
				 * 那么点击 某个点的时候，就让该点需要显示的内容放在这个点的上面
				 * 要是地图对象的坐标是用定位的方式得到的，也就是Location不为0
				 * 那么此时点击那个点的时候显示的内容是此处是用的在地图的对应的位置
				 * 因为要是定位的话，也是一句地图来得到地图对象的，所以使用地图对应的位置刚好合适
				 *
				 * 个人建议使用直接使用地图对应的位置
				 */
			/*
				int objectX=objectModel.getX();
				if (objectX==0)
				{
					objectX=xInMapCoords;
				}
				int objectY=objectModel.getY();
				if (objectY==0)
				{
					objectY=yInMapCoords;
				}
				*/

		        // Calculating position of popup on the screen
	        	int x = xToScreenCoords(xInMapCoords);//使用地图对应的X轴的值
				int y = yToScreenCoords(yInMapCoords) - imgHeight;//使用地图对应的Y轴的值
				
				// Show it
				showLocationsPopup(x, y, objectModel.getCaption());
			} else {
				// This is a case when we want to show popup where the user has touched.
				showLocationsPopup(xInScreenCoords, yInScreenCoords, "Shows where user touched");
			}
			
			// Hint: If user touched more than one object you can show the dialog in which ask
			// the user to select concrete object
		} else {
			if (mapObjectInfoPopup != null) {
				mapObjectInfoPopup.hide();
			}
		}
	}
	
	
    private void showLocationsPopup(int x, int y, String text)
    {
        RelativeLayout mapLayout = (RelativeLayout) findViewById(R.id.rootLayout);

        if (mapObjectInfoPopup != null)//如果当前有显示的内容的话，那么就影藏起来哦
        {
            mapObjectInfoPopup.hide();
        }
		//显示某个点需要显示的内容

        ((TextPopup) mapObjectInfoPopup).setIcon((BitmapDrawable) getResources().getDrawable(R.drawable.map_popup_arrow));
        ((TextPopup) mapObjectInfoPopup).setText(text);

        //处理某个点显示内容的点击事件
		mapObjectInfoPopup.setOnClickListener(new OnTouchListener()
        {
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    if (mapObjectInfoPopup != null)
                    {
                        mapObjectInfoPopup.hide();
                    }
                }
				//下面两句话是显示自己所点击的信息的内部的信息
				/**
				 * 要是想通过点击一个简易的信息，进入到一个更加详细的界面的话，可以使用自己的规则，将需要传递给
				 * 另一个界面的信息，通过&等符号，在之前，变成一个字符串合并起来，只是在显示简易的信息 的时候
				 * 显示主要的部分，信息的ID、map对象的ID，都可以通过合并在分开，进行传递
				 */
				TextView view=(TextView)v;
				Toast.makeText(BrowseMapActivity.this,view.getText().toString(),Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        ((TextPopup) mapObjectInfoPopup).show(mapLayout, x, y);
    }
    
    /***
     * Transforms coordinate in map coordinate system to screen coordinate system
     * @param mapCoord - X in map coordinate in pixels. 
     * @return X coordinate in screen coordinates. You can use this value to display any object on the screen.
     */
    private int xToScreenCoords(int mapCoord)
    {
    	return (int)(mapCoord *  map.getScale() - map.getScrollX());
    }
    
    private int yToScreenCoords(int mapCoord)
    {
    	return (int)(mapCoord *  map.getScale() - map.getScrollY());
    }

	private Location addObjetWhereTouched(final MapWidget mapWidget, MapTouchedEvent event, int iconId) {
		// getting icon from assets
		Drawable icon = getResources().getDrawable(iconId);
		int id=nextObjectId++;
		MapObject obj = new MapObject(id, icon, new Point(0, 0),
				PivotFactory.createPivotPoint(icon, PivotPosition.PIVOT_CENTER), true, false);
		//将地图对象添加到地图的图层上，同时也应该将地图对象model添加到地图对象容器中
		Layer layer = mapWidget.getLayerById(LAYER1_ID);
		layer.addMapObject(obj);

		MapObjectModel mapObjectModel=new MapObjectModel(id,event.getMapX(),event.getMapY(),"新添加的对象点，id="+id);
		model.addObject(mapObjectModel);

		Location location = new Location("custom");
		GeoUtils.translate(mapWidget, event.getMapX(), event.getMapY(), location);

		obj.moveTo(location);
		return location;
	}

	public StringBuffer showLocationInfo(Location location) {
		StringBuffer stringBuffer = new StringBuffer();
		LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
		GeocodeSearch geocodeSearch = new GeocodeSearch(
				BrowseMapActivity.this);
		LatLonPoint point = new LatLonPoint(latLng.latitude,
				latLng.longitude);
		RegeocodeQuery regeocodeQuery = new RegeocodeQuery(
				point, 1000, GeocodeSearch.AMAP);
		RegeocodeAddress address = null;
		try {

			address=geocodeSearch.getFromLocation(regeocodeQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String area = address.getProvince();// 省或直辖市
		String loc = address.getCity();// 地级市或直辖市
		String subLoc = address.getDistrict();// 区或县或县级市
		String ts = address.getTownship();// 乡镇
		String thf = null;// 道路
		List<RegeocodeRoad> regeocodeRoads = address.getRoads();// 道路列表
		if (regeocodeRoads != null && regeocodeRoads.size() > 0) {
			RegeocodeRoad regeocodeRoad = regeocodeRoads.get(0);
			if (regeocodeRoad != null) {
				thf = regeocodeRoad.getName();
			}
		}
		String subthf = null;// 门牌号
		StreetNumber streetNumber = address.getStreetNumber();
		if (streetNumber != null) {
			subthf = streetNumber.getNumber();
		}
		String fn = address.getBuilding();// 标志性建筑,当道路为null时显示
		if (area != null)
			stringBuffer.append("地区"+area);
		if (loc != null && !area.equals(loc))
			stringBuffer.append(",直辖市"+loc);
		if (subLoc != null)
			stringBuffer.append(",区/县"+subLoc);
		if (ts != null)
			stringBuffer.append(",乡镇"+ts);
		if (thf != null)
			stringBuffer.append(",道路"+thf);
		if (subthf != null)
			stringBuffer.append(",门牌号"+subthf);
		if ((thf == null && subthf == null) && fn != null
				&& !subLoc.equals(fn))
			stringBuffer.append(fn + "附近");



		return stringBuffer;
	}

class showLocation extends AsyncTask{
	public Location location;
	public showLocation(Location location)
	{
		this.location=location;
	}

	@Override
	protected Object doInBackground(Object[] objects) {
		StringBuffer stringBuffer=showLocationInfo(location);
		return stringBuffer;
	}

	@Override
	protected void onPostExecute(Object o) {
		showAlertDialog(o.toString());
		super.onPostExecute(o);
	}
}

	public void showAlertDialog(String result)
  {
	  AlertDialog.Builder builder=new AlertDialog.Builder(BrowseMapActivity.this);
	  builder.setMessage(result);
	  builder.create().show();
  }
}