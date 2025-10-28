package com.livraigo.service.optimizer;

import com.livraigo.model.entity.Delivery;
import com.livraigo.model.entity.Warehouse;
import com.livraigo.service.util.DistanceCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ClarkeWrightOptimizer implements TourOptimizer {
    
    private static final Logger logger = LoggerFactory.getLogger(ClarkeWrightOptimizer.class);
    
    private final DistanceCalculator distanceCalculator;
    
    public ClarkeWrightOptimizer(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }
    
    @Override
    public List<Delivery> calculateOptimalTour(Warehouse warehouse, List<Delivery> deliveries) {
        logger.info("Calculating optimal tour using Clarke & Wright algorithm");
        
        if (deliveries.isEmpty()) {
            return new ArrayList<>();
        }
        
        if (deliveries.size() == 1) {
            return new ArrayList<>(deliveries);
        }
        
        List<Savings> savingsList = calculateSavings(warehouse, deliveries);
        
        savingsList.sort((s1, s2) -> Double.compare(s2.savings, s1.savings));
        
        Map<Delivery, Route> deliveryToRoute = new HashMap<>();
        for (Delivery delivery : deliveries) {
            Route route = new Route(delivery);
            deliveryToRoute.put(delivery, route);
        }
        
        for (Savings savings : savingsList) {
            Route route1 = deliveryToRoute.get(savings.delivery1);
            Route route2 = deliveryToRoute.get(savings.delivery2);
            
            if (route1 != route2 && canMerge(route1, route2)) {
                mergeRoutes(route1, route2);
                updateRouteMapping(deliveryToRoute, route1, route2);
            }
        }
        
        Route finalRoute = deliveryToRoute.values().iterator().next();
        List<Delivery> optimizedRoute = buildRouteFromWarehouse(warehouse, finalRoute);
        
        logger.info("Clarke & Wright optimization completed. Route length: {}", optimizedRoute.size());
        return optimizedRoute;
    }
    
    private List<Savings> calculateSavings(Warehouse warehouse, List<Delivery> deliveries) {
        List<Savings> savingsList = new ArrayList<>();
        
        for (int i = 0; i < deliveries.size(); i++) {
            for (int j = i + 1; j < deliveries.size(); j++) {
                Delivery d1 = deliveries.get(i);
                Delivery d2 = deliveries.get(j);
                
                double distanceWarehouseD1 = distanceCalculator.calculateDistance(warehouse, d1);
                double distanceWarehouseD2 = distanceCalculator.calculateDistance(warehouse, d2);
                double distanceD1D2 = distanceCalculator.calculateDistance(d1, d2);
                
                double savings = distanceWarehouseD1 + distanceWarehouseD2 - distanceD1D2;
                savingsList.add(new Savings(d1, d2, savings));
            }
        }
        
        return savingsList;
    }
    
    private boolean canMerge(Route route1, Route route2) {
        return (route1.head == route2.tail) || (route1.tail == route2.head);
    }
    
    private void mergeRoutes(Route route1, Route route2) {
        if (route1.tail == route2.head) {
            route1.tail = route2.tail;
            route2.head.prev = route1.tail;
            route1.tail.next = route2.head;
        } else if (route1.head == route2.tail) {
            route1.head = route2.head;
            route2.tail.next = route1.head;
            route1.head.prev = route2.tail;
        }
    }
    
    private void updateRouteMapping(Map<Delivery, Route> mapping, Route route1, Route route2) {
        Route current = route2.head;
        while (current != null) {
            mapping.put(current.delivery, route1);
            current = current.next;
        }
    }
    
    private List<Delivery> buildRouteFromWarehouse(Warehouse warehouse, Route route) {
        List<Delivery> result = new ArrayList<>();
        Route.Node current = route.head;
        
        while (current != null) {
            result.add(current.delivery);
            current = current.next;
        }
        
        return result;
    }
    
    private static class Savings {
        final Delivery delivery1;
        final Delivery delivery2;
        final double savings;
        
        Savings(Delivery delivery1, Delivery delivery2, double savings) {
            this.delivery1 = delivery1;
            this.delivery2 = delivery2;
            this.savings = savings;
        }
    }
    
    private static class Route {
        Node head;
        Node tail;
        
        Route(Delivery delivery) {
            this.head = new Node(delivery);
            this.tail = this.head;
        }
        
        static class Node {
            final Delivery delivery;
            Node prev;
            Node next;
            
            Node(Delivery delivery) {
                this.delivery = delivery;
            }
        }
    }
}