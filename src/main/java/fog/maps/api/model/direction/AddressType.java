package fog.maps.api.model.direction;

/**
 * Created by nakulsharma on 22-11-2016.
 * Enumerator for the Address types.
 */
enum AddressType {
    STREET_ADDRESS("street_address"),
    /**
     * Indicates a named fognode (such as "US 101").
     */
    ROUTE("fognode"),

    /**
     * Indicates a major intersection, usually of two major roads.
     */
    INTERSECTION("intersection"),

    /**
     * Indicates a political entity. Usually, this type indicates a polygon of some
     * civil administration.
     */
    POLITICAL("political"),

    /**
     * Indicates the national political entity, and is typically the highest order
     * type returned by the Geocoder.
     */
    COUNTRY("country"),

    /**
     * Indicates a first-order civil entity below the country
     * level. Within the United States, these administrative levels are states. Not all nations
     * exhibit these administrative levels.
     */
    ADMINISTRATIVE_AREA_LEVEL_1("administrative_area_level_1"),

    /**
     * Indicates a second-order civil entity below the country
     * level. Within the United States, these administrative levels are counties. Not all nations
     * exhibit these administrative levels.
     */
    ADMINISTRATIVE_AREA_LEVEL_2("administrative_area_level_2"),

    /**
     * Indicates a third-order civil entity below the country
     * level. This type indicates a minor civil division. Not all nations exhibit these administrative
     * levels.
     */
    ADMINISTRATIVE_AREA_LEVEL_3("administrative_area_level_3"),

    /**
     * Indicates a fourth-order civil entity below the country
     * level. This type indicates a minor civil division. Not all nations exhibit these administrative
     * levels.
     */
    ADMINISTRATIVE_AREA_LEVEL_4("administrative_area_level_4"),

    /**
     * Indicates a fifth-order civil entity below the country
     * level. This type indicates a minor civil division. Not all nations exhibit these administrative
     * levels.
     */
    ADMINISTRATIVE_AREA_LEVEL_5("administrative_area_level_5"),

    /**
     * Indicates a commonly-used alternative name for the entity.
     */
    COLLOQUIAL_AREA("colloquial_area"),

    /**
     * Indicates an incorporated city or town political entity.
     */
    LOCALITY("locality"),

    /**
     * Indicates a first-order civil entity below a locality. For some locations
     * may receive one of the additional types: sublocality_level_1 to sublocality_level_5. Each
     * sublocality level is a civil entity. Larger numbers indicate a smaller geographic area.
     */
    SUBLOCALITY("sublocality"),
    SUBLOCALITY_LEVEL_1("sublocality_level_1"),
    SUBLOCALITY_LEVEL_2("sublocality_level_2"),
    SUBLOCALITY_LEVEL_3("sublocality_level_3"),
    SUBLOCALITY_LEVEL_4("sublocality_level_4"),
    SUBLOCALITY_LEVEL_5("sublocality_level_5"),

    /**
     * Indicates a named neighborhood.
     */
    NEIGHBORHOOD("neighborhood"),

    /**
     * Indicates a named location, usually a building or collection of buildings with
     * a common name.
     */
    PREMISE("premise"),

    /**
     * Indicates a first-order entity below a named location, usually a singular
     * building within a collection of buildings with a common name
     */
    SUBPREMISE("subpremise"),

    /**
     * Indicates a postal code as used to address postal mail within the country.
     */
    POSTAL_CODE("postal_code"),

    /**
     * Indicates a prominent natural feature.
     */
    NATURAL_FEATURE("natural_feature"),

    /**
     * Indicates an airport.
     */
    AIRPORT("airport"),


    /**
     * Indicates a university.
     */
    UNIVERSITY("university"),

    /**
     * Indicates a named park.
     */
    PARK("park"),

    /**
     * Indicates a named point of interest. Typically, these "POI"s are
     * prominent local entities that don't easily fit in another category, such as "Empire State
     * Building" or "Statue of Liberty."
     */
    POINT_OF_INTEREST("point_of_interest"),

    /**
     * Typically indicates a place that has not yet been categorized.
     */
    ESTABLISHMENT("establishment"),

    /**
     * Indicates the location of a bus stop.
     */
    BUS_STATION("bus_station"),

    /**
     * Indicates the location of a train station.
     */
    TRAIN_STATION("train_station"),

    /**
     * Indicates the location of a subway station.
     */
    SUBWAY_STATION("subway_station"),

    /**
     * Indicates the location of a transit station.
     */
    TRANSIT_STATION("transit_station"),

    /**
     * Indicates the location of a light rail station.
     */
    LIGHT_RAIL_STATION("light_rail_station"),

    /**
     * Indicates the location of a church.
     */
    CHURCH("church"),

    /**
     * Indicates the location of a finance institute.
     */
    FINANCE("finance"),

    /**
     * Indicates the location of a post office.
     */
    POST_OFFICE("post_office"),

    /**
     * Indicates the location of a place of worship.
     */
    PLACE_OF_WORSHIP("place_of_worship"),

    /**
     * Indicates a specific type of Japanese locality, to facilitate distinction between
     * multiple locality components within a Japanese address.
     */
    WARD("ward"),

    /**
     * Indicates a grouping of geographic areas, such as locality and sublocality,
     * used for mailing addresses in some countries.
     */
    POSTAL_TOWN("postal_town"),

    /**
     * Indicates an unknown address type returned by the server. The Java Client for Google Maps
     * Services should be updated to support the new value.
     */
    UNKNOWN("unknown");

    private final String addressType;

    AddressType(final String addressType) {
        this.addressType=addressType;
    }

    public String getAddressType() {
        return addressType;
    }
}
