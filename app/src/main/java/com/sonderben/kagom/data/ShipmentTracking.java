package com.sonderben.kagom.data;

import java.util.List;

public record ShipmentTracking(float shipmentsStatusPercent,ShipmentsStatus shipmentsStatus, List<ShipmentsStatus> possibleStatus) {
    //private ShipmentsStatus shipmentsStatus;
}
