package com.clinical.management.doctor.parser;

import com.clinical.management.doctor.domain.Weather;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class WeatherParser {

    public List<Weather> parse(JSONObject json) throws JSONException, ParseException {
        List<Weather> allWeathers = getAllWeathers(json);
        return reduceWeathers(allWeathers);
    }

    private List<Weather> getAllWeathers(JSONObject json) throws JSONException, ParseException {
        List<Weather> weathers = new ArrayList<>();
        JSONObject object = json.getJSONObject("city");
        JSONArray list = json.getJSONArray("list");
        String cityName = object.getString("name");
        String country = object.getString("country");

        for (int i = 0; i < list.length(); i++) {
            Weather weather = getWeather(list.getJSONObject(i));
            weather.setCity(cityName);
            weather.setCountry(country);

            weathers.add(weather);
        }

        return weathers;
    }

    private Weather getWeather(JSONObject obj) throws JSONException, ParseException {
        Weather weather = new Weather();
        Date date = convertStringToDate(obj.getString("dt_txt"));
        JSONObject main = obj.getJSONObject("main");
        JSONObject weatherInfo = obj.getJSONArray("weather").getJSONObject(0);

        weather.setDate(date);
        weather.setTempMax(main.getDouble("temp_max"));
        weather.setTempMin(main.getDouble("temp_min"));
        weather.setWeather(weatherInfo.getString("main"));
        weather.setWeatherDescription(weatherInfo.getString("description"));

        return weather;
    }

    private List<Weather> reduceWeathers(List<Weather> weathers) {
        List<Weather> reduceWeathers = new ArrayList<>();

        if (weathers.size() == 0) {
            return reduceWeathers;
        }

        Weather currentWeather = reduceWeathers.get(0);

        for (Weather weather : weathers) {
            if (sameDay(currentWeather, weather)) {
                currentWeather = reduceWeather(currentWeather, weather);
            } else {
                reduceWeathers.add(currentWeather);
                currentWeather = weather;
            }
        }

        return reduceWeathers;
    }

    private Weather reduceWeather(Weather currentWeather, Weather nextWeather) {
        if (currentWeather.getTempMin() > nextWeather.getTempMin()) {
            currentWeather.setTempMin(nextWeather.getTempMin());
        }

        if (currentWeather.getTempMax() < nextWeather.getTempMax()) {
            currentWeather.setTempMax(nextWeather.getTempMax());
        }

        return currentWeather;
    }

    private boolean sameDay(Weather currentWeather, Weather nextWeather) {
        if (currentWeather == null) {
            return false;
        }

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(currentWeather.getDate());
        cal2.setTime(nextWeather.getDate());

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    private Date convertStringToDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.parse(date);
    }

}
