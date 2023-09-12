package com.sonderben.kagom.data

import java.util.Date

data class Shipment (
    val id:Long,
    val status:ShipmentsStatus,
    val distributionOrigin:DistributionCenter,
    val distributionDestination:DistributionCenter,
    val sender:Customer,
    val info:String,
    val trackingId:String,
    val receiver:Customer,
    val sendDate:Long,
    val receiveDate:Long,
    val paid:Boolean,
    /*
    "id": 1,
    "status": "CENTER_DISTRIBUTION",
    "distributionOrigin": {
      "id": 2,
      "name": "Abc",
      "direction": "Haïti Nord Saint-marc  Lascirie # 4"
    },
    "distributionDestination": {
      "id": 2,
      "name": "Abc",
      "direction": "Haïti Nord Saint-marc  Lascirie # 4"
    },
    "sender": {
      "id": 1,
      "fullName": "Ben Pha",
      "email": "customer@gmail.com"
    },
    "receiver": {
      "id": 1,
      "fullName": "Ben Pha",
      "email": "customer@gmail.com"
    },
    "receiveDate": 1693261114373,
    "sendDate": 1693261114373,
    "paid": true
     */
)


