package dao;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import modelo.List;
import modelo.WeatherData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

public class WeatherDAOImpl implements WeatherDAO{
    @Override
    public boolean leerTiempo() throws IOException  {

        try {
            System.out.println("Extrayendo información...");
            URL url = new URL("https://api.openweathermap.org/data/2.5/forecast/daily?q=Galapagar&units=metric&mode=json&appid=479092b77bcf850403cb2aeb1a302425");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));

            Gson gson = new Gson();

            WeatherData weatherData = gson.fromJson(streamReader, WeatherData.class);

            for (List list: weatherData.getList()) {

                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(list.getDt() * 1000);

                String fecha = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR);

                System.out.println("\n*********************");
                System.out.println("\nTemperatura en "+ weatherData.getCity().getName() + " el día: " + fecha);
                System.out.println("\nTemperatura de día: " + list.getTemp().getDay() + " Cº");
                System.out.println("\nTemperatura de día por la mañana: " + list.getTemp().getMorn() + " Cº");
                System.out.println("\nTemperatura de día por la tarde: " + list.getTemp().getEve() + " Cº");
                System.out.println("\nTemperatura de día por la noche: " + list.getTemp().getNight() + " Cº");
                System.out.println("\nTemperatura mínima: " + list.getTemp().getMin() + " Cº");
                System.out.println("\nTemperatura máxima: " + list.getTemp().getMax() + " Cº");
            }

            return true;
        } catch (IOException | JsonSyntaxException  e) {
            e.printStackTrace();
            return false;
        }
    }
}
