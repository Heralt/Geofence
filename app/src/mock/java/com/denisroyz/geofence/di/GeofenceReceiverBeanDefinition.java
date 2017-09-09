package com.denisroyz.geofence.di;

import com.denisroyz.geofence.repository.UserLocationRepository;
import com.denisroyz.geofence.repository.GeofenceRuleRepository;
import com.denisroyz.geofence.service.GeofenceReceiver;
import com.denisroyz.geofence.service.GeofenceReceiverImpl;
import com.denisroyz.geofence.service.ObjectSerializer;

/**
 * Created by Heralt on 06.09.2017.
 */

public class GeofenceReceiverBeanDefinition implements BeanDefinition<GeofenceReceiver> {
    @Override
    public GeofenceReceiver createBean(DIContext diContext) {
        UserLocationRepository currentLocationRepository = (UserLocationRepository) diContext.getBean(BeanTags.USER_LOCATION_REPOSITORY);
        GeofenceRuleRepository geofenceRuleRepository = (GeofenceRuleRepository) diContext.getBean(BeanTags.RULES_REPOSITORY);
        return new GeofenceReceiverImpl(currentLocationRepository, geofenceRuleRepository);
    }

    @Override
    public String getTag() {
        return BeanTags.GEOFENCE_RECEIVER;
    }
}
