package de.exxcellent.challenge.defaults;

/**
 * this class contains default values, which are used within the project
 */
public class DefaultValues {
    public static final String filePathWeather = "src/main/resources/de/exxcellent/challenge/weather.csv";
    public static final String filePathFootball = "src/main/resources/de/exxcellent/challenge/football.csv";

    public static final char csvDelimiter = ',';
    public static final boolean csvHasHeader = true;

    public static final String labelColumnNameStandard = "1";
    public static final String compareValueOneColumnNameStandard = "2";
    public static final String compareValueTwoColumnNameStandard = "3";
    public static final String labelColumnNameWeather = "Day";
    public static final String compareValueOneColumnNameWeather = "MxT";
    public static final String compareValueTwoColumnNameWeather = "MnT";
    public static final String labelColumnNameFootball = "Team";
    public static final String compareValueOneColumnNameFootball = "Goals";
    public static final String compareValueTwoColumnNameFootball = "Goals Allowed";

    public enum DataObjectType {
        WEATHER_DATA, FOOTBALL_DATA, STANDARD
    }
}
