package com.giangtheshy.d.models.Enums;

public enum TypeCoordinate {
     MULTILINESTRING("MultiLineString"),MULTIPOLYGON("MultiPolygon");
     public String value;

     TypeCoordinate(String val) {
          this.value = val;
     }
}
