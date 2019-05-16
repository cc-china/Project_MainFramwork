package com.grirms.crm.crm_module.interfaces;

import com.amap.api.maps.MapView;

/**
 * Created by Administrator on 2019\4\28 0028.
 */

public interface IMapLifeCycleListener {
    void onMapCreate(MapView mapView);
    void onMapPause(MapView mapView);
    void onMapDestroy(MapView mapView);
}
