package com.sonderben.kagom.utils;

import com.sonderben.kagom.data.Address;
import com.sonderben.kagom.data.Customer;
import com.sonderben.kagom.data.DistributionCenter;
import com.sonderben.kagom.entity.AddressEntity;
import com.sonderben.kagom.entity.CustomerEntity;
import com.sonderben.kagom.entity.DistributionEntity;

public class Converter {

    public static CustomerEntity convert(Customer c,long addressId,long distributionId){
        return new CustomerEntity(
                c.getId(),
                c.getCountryIdentity(),
                c.getFullName(),
                c.getEmail(),
                c.getTelephone(),
                c.getBirthday(),
                c.getKmIdentity(),
                c.getPoints(),
                addressId,
                distributionId
        );

    }

    public static Customer convert(CustomerEntity ce){
       Customer c = new Customer();
       c.setCountryIdentity(ce.countryIdentity );
       c.setEmail( ce.getEmail() );
       c.setFullName(ce.getFullName() );
       c.setTelephone(ce.getTelephone() );
       c.setBirthday( ce.getBirthday() );
       c.setKmIdentity( ce.getKmIdentity() );
       c.setId( ce.getId() );
       return c;
    }

    public static DistributionEntity convert(DistributionCenter d,long addressId){
        return new DistributionEntity(d.getId()
                ,d.getName(),
                d.getEmail(),
                d.getTel(),
                d.isInternational(),
                d.getSchedule(),
                addressId );
    }

    public static DistributionCenter convert(DistributionEntity de){
        DistributionCenter d = new DistributionCenter();
        d.setId( de.getId() );
        d.setName( de.getName() );
        d.setInternational( de.isInternational() );
        d.setEmail( de.getEmail() );
        d.setTel( de.getTel() );
       // d.setAddress(address);
        return d;
    }

    public static AddressEntity convert(Address a){
        return new AddressEntity(a.getId(),
                a.getCountry(),
                a.getState(),a.getCity(),
                a.getCodePostal(),
                a.getDirection());
    }

    public static Address convert(AddressEntity ae){
        Address a = new Address();
        a.setId( ae.getId() );
        a.setCountry( ae.getCountry() );
        a.setState( ae.getState() );
        a.setCity( ae.getCity() );
        a.setDirection( ae.getDirection() );
        a.setCodePostal( ae.getCodePostal() );
        return a;

    }


}
