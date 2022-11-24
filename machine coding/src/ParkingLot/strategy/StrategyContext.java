package ParkingLot.strategy;

public class StrategyContext {

    public SearchParkingStrategy getSearchParkingStrategy(String type) {
        if (type.equals(SearchParkingStrategy.SEARCH_NEAR_BY_PARKING_STRATEGY)) {
            return new SearchNearByParkingStrategy();
        }
        return null;
    }
}
