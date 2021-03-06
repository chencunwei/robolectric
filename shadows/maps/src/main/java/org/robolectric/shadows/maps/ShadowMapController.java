package org.robolectric.shadows.maps;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

@Implements(MapController.class)
public class ShadowMapController {
  private ShadowMapView shadowMapView;
  private GeoPoint geoPointAnimatedTo;

  @Implementation
  public void animateTo(com.google.android.maps.GeoPoint geoPoint) {
    setCenter(geoPoint);
    geoPointAnimatedTo = geoPoint;
  }

  @Implementation
  public void animateTo(com.google.android.maps.GeoPoint geoPoint, java.lang.Runnable runnable) {
    animateTo(geoPoint);
    runnable.run();
  }

  @Implementation
  public void setCenter(com.google.android.maps.GeoPoint geoPoint) {
    shadowMapView.mapCenter = geoPoint;
  }

  @Implementation
  public void zoomToSpan(int latSpan, int lngSpan) {
    shadowMapView.latitudeSpan = latSpan;
    shadowMapView.longitudeSpan = lngSpan;
  }

  @Implementation
  public boolean zoomIn() {
    shadowMapView.zoomLevel++;
    return true;
  }

  @Implementation
  public boolean zoomOut() {
    shadowMapView.zoomLevel--;
    return true;
  }

  @Implementation
  public int setZoom(int i) {
    shadowMapView.zoomLevel = i;
    return i;
  }

  /**
   * Returns the {@code MapView} that is being controlled
   *
   * @return the {@code MapView} that is being controlled
   */
  public ShadowMapView getShadowMapView() {
    return shadowMapView;
  }

  /**
   * Returns the most recent value set by a call to either version of {@code animateTo()}
   *
   * @return the most recent value set by a call to either version of {@code animateTo()}
   */
  public GeoPoint getGeoPointAnimatedTo() {
    return geoPointAnimatedTo;
  }

  /**
   * Allows the {@code MapView} being controlled to be set explicitly.
   *
   * @param shadowMapView the {@link ShadowMapView} to be controlled (either created explicitly or obtained via a call
   *                      to {@link org.robolectric.RobolectricForMaps.shadowOf(com.google.android.maps.MapView)})
   */
  void setShadowMapView(ShadowMapView shadowMapView) {
    this.shadowMapView = shadowMapView;
  }
}
